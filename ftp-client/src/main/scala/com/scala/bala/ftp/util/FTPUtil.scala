package com.scala.bala.ftp.util

import com.scala.bala.ftp.exception.BException

object FTPUtil extends BException{
	 
	def getIPAddressFromPASV(pasv:String) = {
		
		val pattern = """.*?(\d+),(\d+),(\d+),(\d+),(\d+),(\d+).*?""".r  
		val pattern(d0,d1,d2,d3,d4,d5) = pasv
	     (d0+"."+d1+"."+d2+"."+d3,  ((d4.toInt <<  8)+d5.toInt) )
	}

	def getCode(output:String):Int = {	   
		println(output)
	   val regx = "(\\d)*".r	  
	   
	   val catchException = getNumberFormatException
					     
	   catchException.apply{ regx.findPrefixOf(output).get.toInt } 
	    
	}
	
	
	def currentPath(response:String):String = {
	   val regx = "\"(.*?)\"".r     
       val bool = regx.findFirstIn(response).mkString      
       bool.replace("\"", "")
	}
	
}