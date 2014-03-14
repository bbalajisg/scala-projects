package com.scala.bala.ftp

import java.net.Socket
import com.scala.bala.ftp.exception.BException
 
import com.scala.bala.ftp.util.FTPConfigurationReader

class OpenConnection extends BException{
 
	def openConnectionWithServer = {
				
		val serverIP = FTPConfigurationReader.severIPs(1)
		val port  = 21
		 
		val catchException = getConnectionException		    
					     
		val socket  =  catchException.apply{new Socket(serverIP, port)}		    
		val isConnected  = socket.isConnected();
		    
		println("Has connected to "); 
		socket 
	}
	
	
	def disconnect = {
	  
	}
  
}