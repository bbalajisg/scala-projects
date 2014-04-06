package com.scala.bala.ftp.util

import scala.Array.canBuildFrom

 /**
  * Bala's simple File Transfer protocol configuration reader
  */
object FTPConfigurationReader extends ResourceUtil{
	  
	/**
	 * Custom special chars
	 */
	val specialChars = Set('*', '@', '&', '=')
	
	println("Reading configuration file....")
	 
	/**
	 * Call to read ftp server configuration file
	 */
	val fileContent:Array[String] = readResourceFile("/config/ftp.properties")
	
	/**
	 * filters of comment and empty lines
	 */
	private val content = basicFilter(fileContent)
	
	/**
	 * Call to convert line to map
	 */ 
	private val mapped =  (for (f <- content; val v = getAsMap(f)) yield v).flatten.toMap	 	
	
	/**
	 * Collected file names
	 */
	private val fileNames = getFileNames(getConfiguration("SERVER_FILES_TO_READ"))
	val fileName = fileNames.mkString.split(",")
	
	/**
	 * Collected IP Addresses.
	 */  
	private val serverIP =  getConfiguration("MACHINE_IP")
	val severIPs = serverIP.mkString.split(",")
	
	/**
	 * Collects userNames from configuration 
	 */
	private val userArray =  getConfiguration("USER_NAME")
	val userNames = userArray.mkString.split(",")
	
	/**
	 * Collects passwords from configuration 
	 */
    private val passwordArray =  getConfiguration("USER_PASSWORD")
    val passwords = passwordArray.mkString.split(",")
    		
    private val folderLoc =  getConfiguration("SERVER_FOLDER_LOCATION")
    val folderLocation = folderLoc
    
	/**
	 * Retrieves value for the given key
	 */
	def getConfiguration(key:String): String = {
		mapped(key);
	}

}