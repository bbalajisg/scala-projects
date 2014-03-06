package com.scala.bala.ftp.util

import scala.Array.canBuildFrom

object FTPConfigurationReader extends ResourceUtil{
	
	/**
	 * Custom special chars
	 */
	val specialChars = Set('*', '@', '&', '=')
	
	println("Reading configuration file....")
	
	/**
	 * Call to read ftp server configuration file
	 */
	val fileContent:Array[String] = readResourceFile("config/ftp.properties")
	
	/**
	 * filters of comment and empty lines
	 */
	private val content = basicFilter(fileContent)
	
	/**
	 * Call to convert line to map
	 */ 
	private val mapped =  (for (f <- content; val v = getAsMap(f)) yield v).flatten.toMap	 	
	   
	/**
	 * Retrieves value for the given key
	 */
	def getConfiguration(key:String): String = {
		mapped(key);
	} 
  
}