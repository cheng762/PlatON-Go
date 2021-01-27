package plugin

import (
	"math/big"

	"github.com/PlatONnetwork/PlatON-Go/common"
)

type issue1625Accounts struct {
	addr   common.Address
	amount *big.Int
}

func NewIssue1625Accounts() ([]issue1625Accounts, error) {
	var accounts = [][]string{
		{"atp1k0aumpg2r6cawq4uqejpfw6m3n5v877pl4vgkj", "10000000000000000000000"},
		{"atp1des5cqfsrsd6nyyp6h6ekghv5xdw0crqrppl8u", "10000000000000000000000"},
		{"atp1kqyfg9ju4fppl090xwf5nxjkg7zj7yc3r43r4x", "10000000000000000000000"},
		{"atp1e7q4z8dkns6vpk8v27f2hghgz8mr242zcl5aze", "10000000000000000000000"},
		{"atp1540hmathq6fm5ynm5f4fp4ny96nkrk6x75r9kl", "10000000000000000000000"},
		{"atp1nufdg2rvche4upfan94kfwvn62mcr22h5sxef6", "10000000000000000000000"},
		{"atp1zpunl3yfp42yazqmqvkna54nrjuu6khfnhhwg2", "10000000000000000000000"},
		{"atp1s64kt76mrvkw40nzq55k7jh2z0zyarupknaav9", "10000000000000000000000"},
		{"atp1t2yvlc55ufylj09pky6vughmcvn3tjdcvcwqrw", "10000000000000000000000"},
		{"atp1flh9ujvleamf0d6afz3f6c34zq57euuqsfvscx", "10000000000000000000000"},
		{"atp1mledwv3xcpah7qkpnppc5ys0n632hjtf3kwwad", "10000000000000000000000"},
		{"atp17su6ygkv6yry0792ad3j3ezxlz39qfw6t7hp6t", "10000000000000000000000"},
		{"atp1323rrm6z3xp3hgf9vh86kykw8cr9nwr97qxd7r", "10000000000000000000000"},
		{"atp1a4j3lu8wd8wl92hhqxmz659nsllwmn6wr9l5r8", "10000000000000000000000"},
		{"atp1usc5zgepmxw3c46wenps0hedxhcepy2dljz6nz", "10000000000000000000000"},
		{"atp1fl8esvfzt3d20lc479py07drr06wzs9mvl090u", "10000000000000000000000"},
		{"atp16xx2vvv87ufe7nyslah8hxnkraml38h7tyqnlf", "10000000000000000000000"},
		{"atp1eyzg43zn3p2q2thp5h4nlfzzuu7wu0pphfvfpt", "10000000000000000000000"},
		{"atp1s668hqh6hsanndfjq4wrqn5c7cdtexdtap2gz4", "10000000000000000000000"},
	}
	Accounts := make([]issue1625Accounts, 0)
	for _, info := range accounts {
		addr, err := common.Bech32ToAddress(info[0])
		if err != nil {
			return nil, err
		}
		amount, _ := new(big.Int).SetString(info[1], 10)

		Accounts = append(Accounts, issue1625Accounts{
			addr, amount,
		})
	}

	return Accounts, nil
}
