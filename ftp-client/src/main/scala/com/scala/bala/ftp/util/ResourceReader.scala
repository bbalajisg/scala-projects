package com.scala.bala.ftp.util

object ResourceReader {

  def readConfigFile = {
  
		  val source = scala.io.Source.fromFile("config/ftp.properties")
		  val lines  =  source.mkString
		  source.close
  }
  
}