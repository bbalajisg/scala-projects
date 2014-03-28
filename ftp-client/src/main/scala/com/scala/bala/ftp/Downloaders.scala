package com.scala.bala.ftp

import akka.actor.Actor
import com.scala.bala.ftp.util.FTPConfigurationReader

 
 
	object Download {	    
  
		case class downloadFiles(credential:Array[String])
		 
	}


class Downloaders extends Actor {
		
		val fileNames = FTPConfigurationReader.fileName	    
		val localPath = FTPConfigurationReader.getConfiguration("LOCAL_FOLDER_LOCATION")
  
		def receive = {
			case Download.downloadFiles(c) =>  downloadFiles(c)	
			 
		}
   
		
		private def downloadFiles(credential:Array[String]) = {
		 
			val client = new FTPClient();
			fileNames.foreach(client.initgetFile(credential, localPath, _))
			 
		} 
}