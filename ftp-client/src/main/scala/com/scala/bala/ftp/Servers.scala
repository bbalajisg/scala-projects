package com.scala.bala.ftp

import scala.actors.Actor
import com.scala.bala.ftp.util.FTPConfigurationReader

case class ServerConnection(c:Array[String])



class Servers() extends Actor{

	val download = new Downloaders()        
		download.start
    
	def act(){
		while(true){
		  
			receive{				  
				case ServerConnection(c) =>  createServerFolders(c)			    
			}
		  
		}
	}
	
	private def createServerFolders(credential:Array[String]) = {
		 
		val fileNames = FTPConfigurationReader.fileName	    
		val localPath = FTPConfigurationReader.getConfiguration("LOCAL_FOLDER_LOCATION")
		
		 val client = new FTPClient(); 
		
		fileNames.foreach(c => download ! downloadFile(client:FTPClient, credential,localPath, c))
		   
		client.logout
		  
	}
}