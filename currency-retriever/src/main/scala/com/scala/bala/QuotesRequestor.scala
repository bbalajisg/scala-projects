package com.scala.bala

import akka.actor.Actor

/**
 * Created by balaji on 30/4/2014.
 */

  object QuotesRequester {
    case class request(c:Array[String])
  }

  class QuotesRequestor extends Actor{

    def receive = {
      case QuotesRequester.request(c) => c.foreach(println)
    }
  }
