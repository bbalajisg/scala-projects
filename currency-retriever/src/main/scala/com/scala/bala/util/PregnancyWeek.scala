package com.scala.bala.util

import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTime


/**
 * Created by balaji on 22/8/2014.
 */
object PregnancyWeek {




  def pregnancyWeek( ) = {
    println("bala")
  }


  def pregnancyWeek(lastPeriod:String) = {
    /*DateTime.now // returns org.joda.time.DateTime
    DateTime.now + 2.months
    DateTime.nextMonth < DateTime.now + 2.months
    (2.hours + 45.minutes + 10.seconds).millis*/
    val formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    val dt = formatter.parseDateTime(lastPeriod);

    println(dt)
  }



}
