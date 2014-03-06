package com.scala.bala.ftp.exception

import java.io.IOException
import java.net.ConnectException
import java.net.Socket

import scala.util.control.Exception.Catch
import scala.util.control.Exception.catching

import com.scala.bala.ftp.util.FtpErrors
  

trait BException {
  
	private class FtpLoginException(msg:String) extends Exception(msg)
	
	def getLoginExcepion(code:Int) = throw new FtpLoginException(FtpErrors.getErrorMessage(code.toString))
	
	def getConnectionException:Catch[Socket] =  catching(classOf[ConnectException]).withApply(e => throw new SystemError("Unable to connect to requested server"))
	  
	def getNumberFormatException:Catch[Int] = catching(classOf[NumberFormatException]).withApply(e => -1)
	  
	def getConnectionException(msg:String) = throw new IOException(msg)
	  
}