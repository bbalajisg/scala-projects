package com.scala.bala.ftp

import scala.actors.Actor


case class downloadFile(client:FTPClient ,credential:Array[String], localPath:String, remotefile: String)

class Downloaders() extends Actor {

  
  def act(){ 
		while(true){
		  
			receive{				  
				case downloadFile(client, credential, localPath, remotefile) => client.initgetFile(credential, localPath, remotefile)	    
			}
		  
		}
	}
   
}