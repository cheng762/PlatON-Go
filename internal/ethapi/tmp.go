package ethapi

import (
	"context"
	"fmt"
	"log"
	"strconv"

	"github.com/PlatONnetwork/PlatON-Go/consensus"

	"github.com/PlatONnetwork/PlatON-Go/common/hexutil"

	"github.com/PlatONnetwork/PlatON-Go/rpc"

	"github.com/tealeg/xlsx"
)

const DefaultViewNumber = uint64(0)

type ViewCountMap map[uint64]uint64

type AnalystEntity struct {
	beginNumber        uint64
	endNumber          uint64
	viewBlockRate      uint64
	viewCountMap       ViewCountMap
	missViewList       []uint64
	totalProduceTime   uint64
	averageProduceTime uint64
	topArray           []uint64
	txCount            uint64
	tps                uint64
}

/*
	output parameter
		diffTimestamp 				本轮epoch的出块总耗时（ms）
		diffTimestamp / diffNumber	平均出块时间（ms）
		topArray					出块耗时排名前10位
		txCount						交易总数
		tps							tps
*/
func AnalystProduceTime(beginNumber uint64, endNumber uint64, backend Backend) (uint64, uint64, []uint64, uint64, uint64) {
	beginHeader, _ := backend.HeaderByNumber(context.Background(), rpc.BlockNumber(beginNumber))
	endHeader, _ := backend.HeaderByNumber(context.Background(), rpc.BlockNumber(endNumber))

	preTimestamp := beginHeader.Time.Uint64()
	topArray := make([]uint64, 0, 250)
	for i := beginNumber + 1; i <= endNumber; i++ {
		header, _ := backend.HeaderByNumber(context.Background(), rpc.BlockNumber(int64(i)))
		diff := header.Time.Uint64() - preTimestamp
		topArray = append(topArray, diff)
		//calculateTopArray(topArray, diff)
		preTimestamp = header.Time.Uint64()
	}

	diffTimestamp := endHeader.Time.Uint64() - beginHeader.Time.Uint64()
	diffNumber := endHeader.Number.Uint64() - beginHeader.Number.Uint64() + 1

	// 交易总数
	txCount := uint64(0)
	bh, _ := backend.HeaderByNumber(context.Background(), rpc.BlockNumber(int64(beginNumber)))
	eh, _ := backend.HeaderByNumber(context.Background(), rpc.BlockNumber(int64(endNumber)))
	for i := beginNumber; i <= endNumber; i++ {
		h, _ := backend.BlockByNumber(context.Background(), rpc.BlockNumber(int64(i)))
		c := hexutil.Uint(len(h.Transactions()))
		txCount = txCount + uint64(c)
	}
	tps := (txCount * 1000) / (eh.Time.Uint64() - bh.Time.Uint64())
	return diffTimestamp, diffTimestamp / diffNumber, topArray, txCount, tps
}

func calculateTopArray(topArray []uint64, value uint64) {
	//startTime := time.Now()
	if value <= topArray[len(topArray)-1] {
		return
	}
	index := len(topArray) - 1
	for i := len(topArray) - 1; i >= 0; i-- {
		if value >= topArray[i] {
			index = i
		}
		if value < topArray[i] {
			break
		}
	}
	//topArray = append(topArray[:deleteIndex], topArray[deleteIndex+1:]...)
	if index == len(topArray)-1 {
		topArray[index] = value
	} else {
		tmpArray := copyArray(topArray, index)
		topArray[index] = value
		for _, v := range tmpArray {
			index++
			topArray[index] = v
		}
	}
	//log.Println("calculateTopArray wasted time:", "time", common.PrettyDuration(time.Since(startTime)))
}

func copyArray(topArray []uint64, fromIndex int) []uint64 {
	tmpArray := make([]uint64, 0, len(topArray)-1)
	for i := fromIndex; i < len(topArray)-1; i++ {
		tmpArray = append(tmpArray, topArray[i])
	}
	return tmpArray
}

/*
	output parameter
		epoch
		viewCountMap	每个view实际出块数量
		missViewList	缺失view
		viewBlockRate	view出块率
*/
func AnalystView(beginNumber uint64, endNumber uint64, engine consensus.Engine) (uint64, ViewCountMap, []uint64, uint64, error) {
	beginQC := engine.GetPrepareQC(beginNumber)
	endQC := engine.GetPrepareQC(endNumber)
	if beginQC.Epoch != endQC.Epoch {
		return 0, nil, nil, 0, fmt.Errorf("Epoch is inconsistent")
	}
	epoch := beginQC.Epoch

	viewCountMap := make(ViewCountMap, 0)
	missViewList := make([]uint64, 0)
	// 每个view实际出块数量
	for i := beginNumber; i <= endNumber; i++ {
		qc := engine.GetPrepareQC(i) // Get PrepareQC by blockNumber
		if count, ok := viewCountMap[qc.ViewNumber]; ok {
			viewCountMap[qc.ViewNumber] = count + 1
		} else {
			viewCountMap[qc.ViewNumber] = 1
		}
	}
	// 缺失view
	for i := DefaultViewNumber; i <= endQC.ViewNumber; i++ {
		if _, ok := viewCountMap[i]; !ok {
			missViewList = append(missViewList, i)
		}
	}
	// view出块率
	viewBlockRate := (endNumber - beginNumber + 1) * 100 / ((endQC.ViewNumber - DefaultViewNumber + 1) * 10)
	return epoch, viewCountMap, missViewList, viewBlockRate, nil
}

func saveExcel(data []*AnalystEntity, resultPath string) {
	file := xlsx.NewFile()
	sheet, err := file.AddSheet("出块统计")
	if err != nil {
		log.Println(err.Error())
	}

	// add title
	row := sheet.AddRow()
	cell_1 := row.AddCell()
	cell_1.Value = "开始块高"
	cell_2 := row.AddCell()
	cell_2.Value = "结束块高"
	cell_3 := row.AddCell()
	cell_3.Value = "view出块率"
	cell_4 := row.AddCell()
	cell_4.Value = "view实际出块数量"
	cell_5 := row.AddCell()
	cell_5.Value = "缺失view"
	cell_6 := row.AddCell()
	cell_6.Value = "出块耗时（ms）"
	cell_7 := row.AddCell()
	cell_7.Value = "平均出块时间（ms）"
	cell_8 := row.AddCell()
	cell_8.Value = "交易总数"
	cell_9 := row.AddCell()
	cell_9.Value = "TPS"
	cell_10 := row.AddCell()
	cell_10.Value = "出块间隔"

	//add data
	for _, d := range data {
		row := sheet.AddRow()
		beginNumber := row.AddCell()
		beginNumber.Value = strconv.Itoa(int(d.beginNumber))
		endNumber := row.AddCell()
		endNumber.Value = strconv.Itoa(int(d.endNumber))
		viewBlockRate := row.AddCell()
		viewBlockRate.Value = strconv.Itoa(int(d.viewBlockRate))
		viewCountMap := row.AddCell()
		viewCountMap.Value = fmt.Sprintf("%v", d.viewCountMap)
		missViewList := row.AddCell()
		missViewList.Value = fmt.Sprintf("%v", d.missViewList)
		totalProduceTime := row.AddCell()
		totalProduceTime.Value = strconv.Itoa(int(d.totalProduceTime))
		averageProduceTime := row.AddCell()
		averageProduceTime.Value = strconv.Itoa(int(d.averageProduceTime))
		txCount := row.AddCell()
		txCount.Value = strconv.Itoa(int(d.txCount))
		tps := row.AddCell()
		tps.Value = strconv.Itoa(int(d.tps))
		topArray := row.AddCell()
		topArray.Value = fmt.Sprintf("%v", d.topArray)
	}
	err = file.Save(resultPath)
	if err != nil {
		log.Printf("Save excel error, err: %s", err.Error())
	}
	log.Println("\n\nExport success")
}
