package com.scala.bala.ftp

import java.net.Socket

class PassiveDataSocket(remoteHost:String, port:Int) {
	
	val socket = new Socket(remoteHost, port)
	 
	def setTimout(timeout:Int) = {
	  socket.setSoTimeout(timeout)
	}
	
	
}