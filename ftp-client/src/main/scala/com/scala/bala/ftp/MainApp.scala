package com.scala.bala.ftp

 
import com.scala.bala.ftp.util._
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinRouter
 

object MainApp {

 
  
  def main(args: Array[String]): Unit = {
   
   /**
    * Call to read configuration file.
    */ 
       
    println("FTP Client started...");
        
    val serverIps = FTPConfigurationReader.severIPs        
    val userName = FTPConfigurationReader.userNames
    val password = FTPConfigurationReader.passwords
        
    val zippedCredentials = (serverIps zip userName) zip password     
    val credentials =  zippedCredentials map {
      
    	case ((a,b),c) => Array(a.trim(),b.trim(),c.trim())
    	
    }
    
     
  /*  val serverDetails = new Servers()        
    serverDetails.start
  
    credentials.foreach( serverDetails ! ServerConnection(_) )*/
    
    
    val system = ActorSystem()
    val server = system.actorOf(Props(new Servers).withRouter(RoundRobinRouter(nrOfInstances = 10)), "Servers")
    
    credentials.foreach( server ! ServerConnection.connect(_) )
          
  } 

}