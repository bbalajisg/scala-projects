package com.scala.bala.ftp.exception

class SystemError(msg:String, exception: Throwable) extends Exception(msg, exception) {

	def this() = this("", null)
	
	def this(msg:String) = this(msg, null)
	
	def this(exception: Throwable) = this("", exception)
}