package com.scala.bala.util

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.util.Date

/**
 * Created by balaji on 20/5/2014.
 */
object TimeZoneConvertor {

  val DATE_TIME_FORMAT = "yyyyMMdd-HH:mm:ss";


   def timestampUTC(date: DateTime): Date = {
     //DateTimeZone.setDefault(DateTimeZone.UTC)
    val time = date.withZoneRetainFields(DateTimeZone.UTC).toDate
     println(time)
     time
  }
}
