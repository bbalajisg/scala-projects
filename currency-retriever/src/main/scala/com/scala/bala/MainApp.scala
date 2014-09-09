package com.scala.bala

import com.scala.bala.util.CurrencyCodes

import akka.routing.RoundRobinRouter
import akka.actor.{Props, ActorSystem}

object MainApp {

  def main(args: Array[String]): Unit = {

    val system = ActorSystem()
    val server = system.actorOf(Props(new QuotesRequestor).withRouter(RoundRobinRouter(nrOfInstances = 10)), "QuotesRequestor")
    server ! QuotesRequester.request(CurrencyCodes.currencyPairs)

  }
}