package com.scala.bala.ftp

import java.net.Socket

import com.scala.bala.ftp.exception.BException

class OpenConnection extends BException{

  
	def openConnectionWithServer = {
	        val serverIP = "";
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