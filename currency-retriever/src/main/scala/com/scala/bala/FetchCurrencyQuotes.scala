package com.scala.bala

import dispatch._
import Defaults._
import com.ning.http.client.ProxyServer
import scala.util.{Failure, Success}
import scala.util.parsing.json.JSON

class FetchCurrencyQuotes {

  val yahooFinanceApiUrl = "http://query.yahooapis.com/v1/public/yql?q=select%20id,Rate,Date,Time,Ask,Bid%20from%20yahoo.finance.xchange%20where%20pair%20in(%22USDCNY%22)&format=json&env=store://datatables.org/alltableswithkeys"

  def fetchCurrencyRatesAsString() = {

    val translateAPI = url(yahooFinanceApiUrl)  setProxyServer(new ProxyServer("proxy.int.stee.com.sg", 8080))

    val response = Http(translateAPI OK as.String)

    response onComplete {
      case Success(json) => parser(json)
      case Failure(error) => println(" Error " +error)
    }

  }


  def parser(data:String) = {

    val languages = JSON.parseFull(data) match {
      case Some(x:Map[String, Map[String, Map[String, Any]]]) => {
        (x.get("query")).last.get("results").last.get("rate").last
      }
      case None => Nil
    }

    println(languages)

  }

}
