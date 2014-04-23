package com.scala.bala

import dispatch._
import Defaults._

import org.json4s._
import org.json4s.jackson.JsonMethods._

object MainApp {


  def main(args: Array[String]): Unit = {

    val translateAPI = url("https://www.googleapis.com/language/translate/v2/")

    val response = Http(translateAPI OK as.String)

    val json = parse(response()) //() is added by Dispatch and forces to await the result forever ==     Await.result(response , forever)

    println("..........")
    println(json)
  }
}