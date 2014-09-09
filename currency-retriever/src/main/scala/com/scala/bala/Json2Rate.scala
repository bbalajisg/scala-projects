package com.scala.bala

import argonaut._, Argonaut._
import com.scala.bala.util._
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

case class Rate(id : String, rate : Double, date : java.util.Date, ask : Double, bid : Double)

object Rate {
  val formatter = DateTimeFormat.forPattern("M/d/yyyy h:ma")

  implicit def RateDecodeJson : DecodeJson[Rate] =
    DecodeJson(cursor => {
      val c = cursor --\ "query" --\ "results" --\ "rate"
      for {
      /* you can also access other fields of the JSON here, e.g.
       * lang <- (cursor --\ "query" --\ "lang").as[String].map(new java.util.Locale(_))
       */
        id <- (c --\ "id").as[String]
        rate <- (c --\ "Rate").as[String].map(_.toDouble)
        date <- (c --\ "Date").as[String]
        time <- (c --\ "Time").as[String]
        ask <- (c --\ "Ask").as[String].map(_.toDouble)
        bid <- (c --\ "Bid").as[String].map(_.toDouble)

      } yield Rate(id, rate, TimeZoneConvertor.timestampUTC(formatter.parseDateTime(date + " " + time)), ask, bid)})
}
