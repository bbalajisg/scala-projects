package com.scala.bala

import dispatch._
import Defaults._
import com.ning.http.client.ProxyServer
import scala.util.{Failure, Success}
import scala.util.parsing.json.JSON


/**
 * Created by balaji on 12/5/2014.
 */
class CurrencyQuotesFetcher {

  def fetchCurrencyRatesAsString(currencyPair:String) = {

    //val yahooFinanceApiUrl = s"http://query.yahooapis.com/v1/public/yql?q=select%20id,Rate,Date,Time,Ask,Bid%20from%20yahoo.finance.xchange%20where%20pair%20in(%22$currencyPair%22)&format=json&env=store://datatables.org/alltableswithkeys"

    val yahooFinanceApiUrl =  "http://query.yahooapis.com/v1/public/yql?q=select%20id,Rate,Date,Time,Ask,Bid%20from%20yahoo.finance.xchange%20where%20pair%20in(%22USDINR%22)&format=json&env=store://datatables.org/alltableswithkeys"

    val translateAPI = url(yahooFinanceApiUrl)

    val response = Http(translateAPI OK as.String)

    response onComplete {
      case Success(json) => parser(json)
      case Failure(error) => println(" Error " +error)
    }

  }


  def parser(data:String) = {

    println(data)
    val languages = JSON.parseFull(data) match {
      case Some(x:Map[String, Map[String, Map[String, Any]]]) => {
        (x.get("query")).last.get("results").last.get("rate").last
      }
      case None => Nil
    }

    println(languages)

  }

}
