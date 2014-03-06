package com.scala.bala.ftp

import java.io._
import scala.collection.immutable.Set

import com.scala.bala.ftp.exception.BException
import com.scala.bala.ftp.util.FTPConfigurationReader
import com.scala.bala.ftp.util.ResourceUtil 

import  scala.util.control.Exception._;
  

class FTPClient extends BException{
  
	private val ASCII = 0;
	private val BINARY = 1;
	
	private var modeChage = false;
	private var ftpIn:DataInputStream = null;	
	private var ftpOut:PrintStream = null;
	
	
	private var transferMode:Int = BINARY;
	private var code:Int = -1;
	
	private var loggedin = false;	  
	private val fileNames = getFileNames(FTPConfigurationReader.getConfiguration("FILES_TO_READ"))
	println(fileNames)
	  
	def startClient = {
	  
		val user = FTPConfigurationReader.getConfiguration("USER_NAME")
		val password = FTPConfigurationReader.getConfiguration("USER_PASSWORD")
			
		val connection = new OpenConnection;
		val socket = connection.openConnectionWithServer
			
		ftpIn = new DataInputStream(socket.getInputStream());	 
		ftpOut = new PrintStream (socket.getOutputStream());
			
		var code:Int = readLine
		if(code != 220 )  getConnectionException
		  
		login( user, password )
		
		doCmd("PWD\n")
		readLine
		
		changeDirectory(FTPConfigurationReader.getConfiguration("FOLDER_LOCATION"))
		 
		logout
		
		
	}
	
	def login(userName:String, password:String )= {
	    
		doCmd("USER "+userName+"\n")
		
		code = readLine
		if(code != 331 )  getLoginExcepion(code)
		   
		doCmd("PASS "+password+"\n")
		code = readLine
		if(code != 230 )  getLoginExcepion(code)
		
		loggedin = true;
	}
	
	def changeDirectory(logLocation:String) = {
	 
	  
	  doCmd("CWD "+logLocation+"\n")
	  code = readLine
	  
	  if(code != 250 && code != 200)  getConnectionException("Couldn't chage dir to "+ logLocation)
	  
	  doCmd("PWD\n")
	  readLine
 	  
	}
	
	def logout = {
	  
		doCmd("QUIT\n")
		readLine
		ftpIn.close()
		ftpOut.close()
		
		loggedin = false;
	  
	}
	
	def setTransferMode(mode:Int) = {
	   if( (mode != transferMode) && ((mode == ASCII ) || (mode == BINARY ))){
		   transferMode = mode
	   }
	}
	 
	private def readLine:Int = {
		getCode( ftpIn.readLine() )			
	}
	
	private def getCode(output:String):Int = {	   
		println(output)
	   val regx = "(\\d)*".r	  
	   
	   val catchException = getNumberFormatException
					     
	   catchException.apply{ regx.findPrefixOf(output).get.toInt } 
	    
	}
	
	def doCmd(cmd:String) = {
	  
		println( cmd)
	   
		ftpOut.print(cmd)
		 
	}
	
	
	def getFileNames(filesNames:String):Array[String]  = {
	  val Pattern = " ".r  
	  val pairs =  filesNames filterNot FTPConfigurationReader.specialChars
	  Pattern.split(pairs)
	}
	
	
	def hardOut = {
	   
	 catching(classOf[IOException]).apply({
	    ftpOut.close();
	    ftpIn.close();
	  })
	   
	}
	
}