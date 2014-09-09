package com.scala.bala

import java.net.URLEncoder
import play.api.libs.ws.WS
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._


/**
 * Created by balaji on 12/5/2014.
 */
class CurrencyQuotesFetcher {

  def fetchCurrencyRatesAsString(currencyPair:String) = {

    implicit val timeout = Timeout(500000 milliseconds)

    val yahooFinanceApiUrl = s"http://query.yahooapis.com/v1/public/yql?q=select%20id,Rate,Date,Time,Ask,Bid%20from%20yahoo.finance.xchange%20where%20pair%20in(%22$currencyPair%22)&format=json&env=store://datatables.org/alltableswithkeys"



    val jsonContainingCurrency = WS.url(yahooFinanceApiUrl).get()

    val future = jsonContainingCurrency map {
      response => (response.json)
    }

    val result = Await.result(future, timeout.duration).asInstanceOf[List[play.api.libs.json.JsObject]]

     println(  result  )

  }
}
