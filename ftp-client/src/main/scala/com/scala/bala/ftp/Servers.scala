package com.scala.bala.ftp

import akka.actor.Actor
import com.scala.bala.ftp.util.FTPConfigurationReader
import akka.actor.Props
import akka.routing.RoundRobinRouter
 
	object ServerConnection {	    
		case class connect(c:Array[String])	    
		 
	}
	
	class Servers() extends Actor{
		
		 val download = context.actorOf(Props(new Downloaders).withRouter(RoundRobinRouter(nrOfInstances = 10)), "downloaders")
	  
		def receive = {
			case ServerConnection.connect(c) =>  createServerFolders(c)	
			 
		}
		 
		private def createServerFolders(credential:Array[String]) = {
			
			 download !  Download.downloadFiles(credential) 
			 
		} 
   
	}
	
	 
