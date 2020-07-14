package main

import (
	"log"
	"net/http"

	"golang.org/x/net/websocket"
)

func echoHandler(ws *websocket.Conn) {
	defer ws.Close()
	for {
		var msg map[string][]interface{}
		if err := websocket.JSON.Receive(ws, &msg); err != nil {
			log.Println(err)
		}

		switch msg["emit"][0] {
		case "hello":
			var response map[string][]interface{} = make(map[string][]interface{})
			response["emit"] = []interface{}{"ready"}
			if err := websocket.JSON.Send(ws, response); err != nil {
				log.Println(err)
			}
		case "node-ping":
			var response map[string][]interface{} = make(map[string][]interface{})
			response["emit"] = []interface{}{"node-pong", "ok"}
			if err := websocket.JSON.Send(ws, response); err != nil {
				log.Println(err)
			}

		case "block":

			block := msg["emit"][1].(map[string]interface{})

			tmp := block["block"].(map[string]interface{})
			if tmp["transactions"] != nil {
				txs := tmp["transactions"].([]interface{})
				log.Printf("block info,  num:%v  ,  txs:%v  ,  interval:%v  ,  miner:%v  ", tmp["number"], len(txs), tmp["interval"], tmp["node_id"])
			} else {
				log.Printf("block info,num:%vinterval:%v,miner:%v", tmp["number"], tmp["interval"], tmp["node_id"])
			}
		case "pending":
			stats := msg["emit"][1].(map[string]interface{})
			log.Println("txpool info,", stats["stats"])
		}
	}

}

func main() {
	http.Handle("/api", websocket.Handler(echoHandler))

	err := http.ListenAndServe(":8080", nil)

	if err != nil {
		panic("ListenAndServe: " + err.Error())
	}
}
