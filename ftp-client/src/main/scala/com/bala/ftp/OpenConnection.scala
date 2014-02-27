package com.bala.ftp

import scala.util.control.Exception.catching
import java.net.Socket
import com.bala.ftp.exception._

class OpenConnection extends BException{

  
	def openConnectionWithServer = {
	        val serverIP = "10.5.3.3";
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