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
	SetSleep      bool
	SleepTime     time.Time
}

func (p *PriAccount) setSleep() {
	p.SleepTime = time.Now()
	p.SetSleep = true
}

func (p *PriAccount) active() {
	p.SetSleep = false
}

func (p *PriAccount) reset() {
	p.SetSleep = false
	p.Nonce = p.ReceiptsNonce + 1
}

func (p *PriAccount) isSleep() bool {
	if p.SetSleep {
		return true
	}
	return false
}

type TxMakeManger struct {
	accounts map[common.Address]*PriAccount
	toPool   []common.Address

	accountSize uint
	singer      types.EIP155Signer
	ReceiptCh   chan types.Receipts // Channel to receive new Receipt
}

func (t *TxMakeManger) MakeTx(perTx int, timetx int, eachAmount, gasPrice *big.Int, txch chan []*types.Transaction, exitCh chan struct{}) {
	shouldmake := time.NewTicker(time.Millisecond * time.Duration(timetx))
	shouldReport := time.NewTicker(time.Second * 3)
	length := len(t.toPool)

	lowpoint := 10000
	uppoiont := 8000
	for {
		select {
		case <-shouldmake.C:
			now := time.Now()
			txs := make([]*types.Transaction, 0)
			for _, account := range t.accounts {
				if account.isSleep() {
					if time.Since(account.SleepTime) >= time.Second*60 {
						log.Debug("wait account 60s", "account", account.Address, "nonce", account.Nonce)
						account.reset()
					}
					continue
				}

				toAdd := t.toPool[rand.Intn(length)]
				tx := types.NewTransaction(account.Nonce, toAdd, eachAmount, 30000, gasPrice, nil)
				newTx, err := types.SignTx(tx, t.singer, account.Priv)
				if err != nil {
					log.Crit(fmt.Errorf("sign error,%s", err.Error()).Error())
				}
				txs = append(txs, newTx)

				account.setSleep()
				if len(txs) >= perTx {
					break
				}
			}
			if len(txs) != 0 {
				log.Trace("make Transaction time use", "use", time.Since(now), "txs", len(txs))
				txch <- txs
			}
		case <-shouldReport.C:
			sleepAccount := 0
			for _, account := range t.accounts {
				if account.isSleep() {
					sleepAccount++
				}
			}
			if sleepAccount >= lowpoint {
				perTx = perTx - 2

			} else if sleepAccount <= uppoiont {
				perTx = perTx + 2

			}
			log.Debug("MakeTx info", "sleepAccount", sleepAccount, "perTx", perTx)
		case <-exitCh:
			shouldmake.Stop()
			//shouldReport.Stop()
			//log.Debug("MakeTransaction exit")
			//return
		}
	}
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
		t.accounts[address] = &PriAccount{privateKey, nonce, address, nonce, false, time.Time{}}
	}
	t.toPool = make([]common.Address, 0)
	for _, pri := range priKey {
		address, err := common.Bech32ToAddress(pri.Add)
		if err != nil {
			log.Crit("NewTxMakeManger Bech32ToAddress fail", "err", err)
		}
		t.toPool = append(t.toPool, address)
	}
	t.singer = singer
	t.ReceiptCh = rech
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
			case txs := <-txsCh:
				pool.txFeed.Send(NewTxsEvent{txs})
			case <-pool.maketxExitCh:
				log.Debug("MakeTransaction txfeed exit")
				//return
			}
		}
	}()

	go func() {
		for {
			select {
			case res := <-txm.ReceiptCh:
				for _, receipt := range res {
					if account, ok := txm.accounts[receipt.Address]; ok {
						if receipt.Nonce != account.Nonce {
							log.Error("get receipt nonce not compare current nonce", "current", account.Nonce, "receipt", receipt.Nonce)
						} else {
							account.ReceiptsNonce = receipt.Nonce
							account.Nonce = receipt.Nonce + 1
							account.active()
						}
					}
				}
				sleepAccount := 0
				for _, account := range txm.accounts {
					if account.isSleep() {
						sleepAccount++
					}
				}
				log.Debug("MakeTransaction receive info", "sleepAccount", sleepAccount)
			case <-pool.maketxExitCh:
				log.Debug("MakeTransaction get receipt nonce  exit")
				//return
			}
		}
	}()

	log.Info("begin to MakeTransaction")
	gasPrice := new(big.Int).SetInt64(50000000000)
	amount := new(big.Int).SetInt64(1)
	go txm.MakeTx(txPer, txTime, amount, gasPrice, txsCh, pool.maketxExitCh)
	return nil
}

func (pool *TxPool) StopMakeTraction() {
	close(pool.maketxExitCh)
}
