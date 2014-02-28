package com.scala.bala.ftp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
  

class FTPClient {

  def startClient = {
    
		 val connection =  new OpenConnection;
		 val socket = connection.openConnectionWithServer
		 
		 val dataInputStream  = new DataInputStream(socket.getInputStream())
		 val dataoutputStream  = new DataOutputStream(socket.getOutputStream())
		   
		 val reader = new BufferedReader(new InputStreamReader(dataInputStream))		 
		 val writer = new BufferedWriter(new OutputStreamWriter(dataoutputStream))
	 
		 
	
	     if(!readLine.startsWith("220")){
	    	 connection.getConnectionException("Unknown response while conneting to server");
	     }
		  
		 sendLine("USER "+ "")
		 
		 /*if(!readLine.startsWith("220")){
	    	 connection.getConnectionException("Unknown response while conneting to server");
	     }*/
		 
		 
		 def sendLine(line:String) = {
		   
		   writer.write(line + "\r\n")
		   writer.flush	   
		   
		 }
		 
		 println(readLine)
		 
		 def readLine:String = {
		   
				reader.readLine()
				 
		   
		 }
		 
		 
  }
  
  
	  
}