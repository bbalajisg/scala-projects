package com.scala.bala.ftp.util

import scala.io.Source

trait ResourceUtil {
	 
	
	/**
	 * Takes in line by line and converts to mapped objects
	 * line with "=" with it
	 * Example:
	 * USERNAME=BALA etc.,
	 */
	def getAsMap(line:String): Map[String, String] = {
	 
	  val Pattern = "=".r
			  
	  val pairs = Pattern.split(line).grouped(2)
	  
	  val mapped = pairs.map {
			case Array(k, v) => k -> v
	  }
	  
	  mapped.toMap
	  
	}
	   
	   
	/**
	 * Reads configuration file.
	 * @param fileName filename with path
	 */
	def readResourceFile(fileName:String):Array[String]  = {

		val source =  Source.fromURL(getClass.getResource(fileName))
		
		val lines = source.getLines.toArray
		source.close
		
		lines
	}
	
	/**
	 * filters of comment and empty lines
	 */
	def basicFilter(line:Array[String]):Array[String] = {
	   
		line filter (! _.startsWith("#") ) filter (_ != "")	 
	    
	}
	
	/**
	 * filename separator
	 * Example  ers*.log,ers.log*.1,ers.log.2,ers.log.3
	 */
	def getFileNames(names:String):Array[String]  = {
	  val Pattern = " ".r  
	  val pairs =  names filterNot FTPConfigurationReader.specialChars
	  Pattern.split(pairs)
	}
}