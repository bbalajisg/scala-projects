package com.scala.bala.ftp

import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintStream
import scala.util.control.Exception.catching
import com.scala.bala.ftp.exception.BException
import com.scala.bala.ftp.util.FTPConfigurationReader
import com.scala.bala.ftp.util.FTPUtil
import java.io.PrintWriter
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.File

class FTPClient extends BException{
  
	private val ASCII = 0;
	private val BINARY = 1;
	
	private var modeChage = false;
	private var ftpIn:DataInputStream = null;	
	private var ftpOut:PrintStream = null;
	
	
	private var transferMode:Int = BINARY;
	private var code:Int = -1;
	
	private var loggedin = false;	  
 
	  
	def startClient = {
	    
		connectToServerNlogin();
		
		/**doCmd("PWD\n")
		readLine
				
		changeDirectory(FTPConfigurationReader.getConfiguration("SERVER_FOLDER_LOCATION"))
		
		doCmd("TYPE I\n")
		readLine*/ 
		 
		logout
		 
	}
	
	
	private def  createDataSocketPASV :PassiveDataSocket = {
	 
		doCmd("PASV\n")
		
		val pasv = ftpIn.readLine();
		val code = FTPUtil.getCode(pasv)
		
		if(code==425) getConnectionException(pasv)
	 
		val ipAddressPort = FTPUtil.getIPAddressFromPASV(pasv) 
		new PassiveDataSocket(ipAddressPort._1, ipAddressPort._2 )
		 
	}
	
	def initgetFile(localPath:String, remotefile: String) = {
	   
		val socket = createDataSocketPASV.socket
		
		doCmd("RETR "+remotefile+"\r\n")
		  
		val joop = scala.io.Source.fromInputStream(socket.getInputStream())		  
		val writer =  new FileWriter(new File(localPath))
		
		try{
			joop.foreach(writer.write(_))
		}finally{
		  writer.close()
		}
		
	}
	
	private def connectToServerNlogin() = {
		 	
		val connection = new OpenConnection;
		val socket = connection.openConnectionWithServer
			
		ftpIn = new DataInputStream(socket.getInputStream());	 
		ftpOut = new PrintStream (socket.getOutputStream());
			
		var code:Int = readLine
		if(code != 220 )  getConnectionException
		  
		 
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
		FTPUtil.getCode( ftpIn.readLine() )			
	}
	
 
	
	def doCmd(cmd:String) = {	  
		println( cmd)	   
		ftpOut.print(cmd)	 
	}
	 
	
	def hardOut = {
	   
	 catching(classOf[IOException]).apply({
	    ftpOut.close();
	    ftpIn.close();
	  })
	   
	}
	
	def getFile(){
		 transferMode match {
		   case ASCII => doCmd("TYPE A\n")
		   case BINARY => doCmd("TYPE I\n")
		 }		 
		 if(readLine != 200)  getConnectionException("Couldn't change xfer")
	}
}