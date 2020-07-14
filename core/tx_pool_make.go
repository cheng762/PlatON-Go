package core

import (
	"crypto/ecdsa"
	"encoding/json"
	"fmt"
	"math/big"
	"math/rand"
	"os"
	"time"

	"github.com/PlatONnetwork/PlatON-Go/common"
	"github.com/PlatONnetwork/PlatON-Go/core/state"
	"github.com/PlatONnetwork/PlatON-Go/core/types"
	"github.com/PlatONnetwork/PlatON-Go/crypto"
	"github.com/PlatONnetwork/PlatON-Go/log"
)

type PriAccount struct {
	Priv    *ecdsa.PrivateKey
	Nonce   uint64
	Address common.Address

	ReceiptsNonce uint64
	SendTime      time.Time
}

type TxMakeManger struct {
	accounts      map[common.Address]*PriAccount
	sleepAccounts map[common.Address]struct{}
	toPool        []common.Address

	accountSize uint
	singer      types.EIP155Signer
	ReceiptCh   chan types.Receipts // Channel to receive new Receipt
	ReceiptTime time.Time
}

func NewTxMakeManger(pendingState *state.ManagedState, accountPath string, start, end int, singer types.EIP155Signer, rech chan types.Receipts) *TxMakeManger {
	file, err := os.Open(accountPath)
	if err != nil {
		log.Crit("Failed to read genesis file", "err", err)
	}
	defer file.Close()

	var priKey []PriKeyJson
	if err := json.NewDecoder(file).Decode(&priKey); err != nil {
		log.Crit("invalid genesis file chain id", "err", err)
	}

	t := new(TxMakeManger)
	t.accounts = make(map[common.Address]*PriAccount)
	t.toPool = make([]common.Address, 0)

	for i := start; i <= end; i++ {
		privateKey, err := crypto.HexToECDSA(priKey[i].Pri)
		if err != nil {
			log.Crit("NewTxMakeManger HexToECDSA fail", "err", err)
		}
		address, err := common.Bech32ToAddress(priKey[i].Add)
		if err != nil {
			log.Crit("NewTxMakeManger Bech32ToAddress fail", "err", err)
		}
		nonce := pendingState.GetNonce(address)
		t.accounts[address] = &PriAccount{privateKey, nonce, address, nonce, time.Now()}
		t.toPool = append(t.toPool, address)
	}
	t.singer = singer
	t.ReceiptCh = rech
	t.sleepAccounts = make(map[common.Address]struct{})
	return t
}

type PriKeyJson struct {
	Pri string `json:"private_key"`
	Add string `json:"address"`
}

func (pool *TxPool) MakeTransaction(txPer, txTime int, accountPath string, start, end int, rech chan types.Receipts) error {

	singine := types.NewEIP155Signer(new(big.Int).SetInt64(pool.chainconfig.ChainID.Int64()))
	txm := NewTxMakeManger(pool.State(), accountPath, start, end, singine, rech)

	txsCh := make(chan []*types.Transaction, 1)
	pool.maketxExitCh = make(chan struct{})

	go func() {
		for {
			select {
			case res := <-txm.ReceiptCh:
				txm.ReceiptTime = time.Now()
				if len(res) > 0 {
					for _, receipt := range res {
						if account, ok := txm.accounts[receipt.Address]; ok {
							account.ReceiptsNonce = receipt.Nonce
						}
					}
				}
			case txs := <-txsCh:
				pool.txFeed.Send(NewTxsEvent{txs})
			case <-pool.maketxExitCh:
				log.Debug("MakeTransaction get receipt nonce  exit")
				return
			}
		}
	}()

	log.Info("begin to MakeTransaction")
	gasPrice := new(big.Int).SetInt64(50000000000)
	amount := new(big.Int).SetInt64(1)

	go func() {
		shouldmake := time.NewTicker(time.Millisecond * time.Duration(txTime))
		shouldReport := time.NewTicker(time.Second * 10)
		length := len(txm.toPool)

		//	lowpoint := 4000
		//	uppoiont := 3000
		for {
			if time.Since(txm.ReceiptTime) >= 10*time.Second {
				log.Debug("MakeTx should sleep", "time", time.Since(txm.ReceiptTime))
				time.Sleep(time.Second * 5)
				continue
			}
			select {
			case <-shouldmake.C:
				now := time.Now()
				txs := make([]*types.Transaction, 0)
				toAdd := txm.toPool[rand.Intn(length)]
				for _, account := range txm.accounts {
					if account.Nonce >= account.ReceiptsNonce+10 {
						if time.Since(account.SendTime) >= time.Second*20 {
							log.Debug("wait account 20s", "account", account.Address, "nonce", account.Nonce, "receiptnonce", account.ReceiptsNonce, "wait time", time.Since(account.SendTime))
							account.Nonce = account.ReceiptsNonce + 1
							delete(txm.sleepAccounts, account.Address)
						} else {
							if _, ok := txm.sleepAccounts[account.Address]; !ok {
								txm.sleepAccounts[account.Address] = struct{}{}
							}
							continue
						}
					} else {
						delete(txm.sleepAccounts, account.Address)
					}
					tx := types.NewTransaction(account.Nonce, toAdd, amount, 30000, gasPrice, nil)
					newTx, err := types.SignTx(tx, txm.singer, account.Priv)
					if err != nil {
						log.Crit(fmt.Errorf("sign error,%s", err.Error()).Error())
					}
					txs = append(txs, newTx)
					account.Nonce++
					account.SendTime = time.Now()
					if len(txs) >= txPer {
						break
					}
				}
				if len(txs) != 0 {
					log.Debug("make Transaction time use", "use", time.Since(now), "txs", len(txs))
					txsCh <- txs
				}
			case <-shouldReport.C:
				sleepAccountsLength := len(txm.sleepAccounts)
				log.Debug("MakeTx info", "sleepAccount", sleepAccountsLength, "perTx", txPer)
			case <-pool.maketxExitCh:
				shouldmake.Stop()
				shouldReport.Stop()
				log.Debug("MakeTransaction exit")
				return
			}
		}
	}()
	return nil
}

func (pool *TxPool) StopMakeTraction() {
	close(pool.maketxExitCh)
}
