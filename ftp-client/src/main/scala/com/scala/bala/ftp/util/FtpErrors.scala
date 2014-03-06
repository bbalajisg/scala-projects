package com.scala.bala.ftp.util
 

object FtpErrors extends Enumeration with ResourceUtil{
  
	println("Reading ftp error message file....")
	 	
	/**
	 * Call to read error message file
	 */
	private val fileContent = readResourceFile("config/ftp_error_messages.properties")
	
	
	private val content = basicFilter(fileContent)
	  
	/**
	 * Call to convert line to map
	 */ 
	private val mapped =  (for (f <- content; val v = getAsMap(f)) yield v).flatten.toMap	 
  
	  
	def getErrorMessage(code:String):String = {	
		mapped(code)
	}
	 
}