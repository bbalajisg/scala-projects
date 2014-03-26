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
import java.util.regex.ASCII 
import java.net.Socket

class FTPClient extends BException{
   
	private var ftpIn:DataInputStream = null;	
	private var ftpOut:PrintStream = null;
	 
	private var transferMode = TransferType.BINARY
	private var code:Int = -1;
	
	private var loggedin = false;	  
	private var isPasv = false;
	private var socket:Socket = null;
	
	private var remoteHost:String = null;
	private var port:Int = 0;
	  
	def  createDataSocketPASV  = {
	 
		doCmd("PASV\n")
		
		val pasv = ftpIn.readLine();		
		val code = FTPUtil.getCode(pasv)
		 
		if(code == 425 ) getConnectionException(pasv)
		  
		if(code == 227 ) {
		    
			val ipAddressPort = FTPUtil.getIPAddressFromPASV(pasv)			 
			remoteHost = ipAddressPort._1
			port = ipAddressPort._2 
			socket = new PassiveDataSocket(remoteHost,  port ).socket		   
		}		
		 
	}
	
	
	def initgetFile(credential:Array[String], localPath:String, remotefile: String) = {
		
		login(credential(0), credential(1), credential(2))
		
		val remoteLocation = getCurrentLocation+"/"
		val serverLogLocation=remoteLocation + FTPConfigurationReader.folderLocation
		 
		changeDirectory( serverLogLocation)
		 
	    setTransferMode(TransferType.BINARY)
	   
	    createDataSocketPASV
	  
		doCmd("RETR "+serverLogLocation+"/"+remotefile+"\r\n")
	     
		val joop = scala.io.Source.fromInputStream(socket.getInputStream())	  
		val serverFolder = new File(localPath+socket.getInetAddress())
	    serverFolder.mkdir()
		val writer =  new FileWriter(serverFolder.getAbsolutePath()+"\\"+remotefile)
		
		try{
			joop.foreach(writer.write(_))
		}finally{
		   writer.flush
		   writer.close	    
		}
		 
	}
	
	private def connectToServer(serverIp:String) = {
		 	
		val connection = new OpenConnection;
		val socket = connection.openConnectionWithServer(serverIp)
			
		ftpIn = new DataInputStream(socket.getInputStream());	 
		ftpOut = new PrintStream (socket.getOutputStream());
			
		var code:Int = readLine
		if(code != 220 )  getConnectionException
		  
		 
	}
	
	def login(serverIp:String, userName:String, password:String )= {
	  
		println("Connecting to.... "+  serverIp + "   " +userName + "  "+ password );
	    
		connectToServer(serverIp)
			      
		doCmd("USER "+userName+"\n")
		
		code = readLine
		if(code != 331 )  getLoginExcepion(code)
		   
		doCmd("PASS "+password+"\n")
		code = readLine
		if(code != 230 )  getLoginExcepion(code)
		   
		loggedin = true;
		  
		doCmd("PWD\n")
		readLine
		  
	}
	
	def getCurrentLocation = {		 
	  
		 doCmd("PWD\n")
		 FTPUtil.currentPath(ftpIn.readLine())
	 
	}
	
	def changeDirectory(logLocation:String) = {
	 
	  
		  doCmd("CWD "+logLocation+"\n")
		  code = readLine
		 
		  if(code != 250 && code != 200)  getConnectionException("Couldn't change dir to "+ logLocation)
		  
	}
	
	def logout = {
	  
		doCmd("QUIT\n")
		readLine
		ftpIn.close()
		ftpOut.close()
		
		loggedin = false;
	  
	}
	 
	
	def setTransferMode(mode:TransferType.Value)  = mode match {
	    
		   case TransferType.BINARY =>  changeMode("TYPE I\n");	   			 
		   case TransferType.ASCII	=> changeMode("TYPE A\n");	   
		   case TransferType.EBCDIC	=> changeMode("TYPE E\n"); 	   
		   case TransferType.LOCAL	=> changeMode("TYPE L\n");  
	   
	}
	
	private def changeMode(mode:String) = {	  
	  doCmd(mode);
	  readLine;
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
		 setTransferMode(transferMode)		   
		 if(readLine != 200)  getConnectionException("Could not change xfer")
	}
	
}