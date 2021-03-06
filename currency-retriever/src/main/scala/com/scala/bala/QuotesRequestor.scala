package com.scala.bala

import akka.actor.Actor

/**
 * Created by balaji on 30/4/2014.
 */

  object QuotesRequester {
    case class request(c:Array[String])
  }

  class QuotesRequestor extends Actor{
     val  fc = new CurrencyQuotesFetcher
    def receive = {
      case QuotesRequester.request(c) => c.foreach(fc.fetchCurrencyRatesAsString(_))
    }
  }
