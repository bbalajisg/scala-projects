package com.bala.ftp.exception

import java.io.IOException
import java.net.{Socket, ConnectException}

import scala.util.control.Exception.{catching, Catch}
  

trait BException {

	 def getConnectionException:Catch[Socket] = {	   
	   
	   import scala.util.control.Exception.catching	
	   catching(classOf[ConnectException]).withApply(e => throw new SystemError("Unable to connect to requested server"))   
	     
	}
	 
	def getConnectionException(msg:String) = {
		 throw new IOException(msg)
	}
	 
 
 
}