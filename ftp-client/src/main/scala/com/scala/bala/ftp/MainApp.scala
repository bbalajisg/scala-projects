package com.scala.bala.ftp

 
import com.scala.bala.ftp.util._
import java.util.regex.Pattern
import scala.actors.Actor
 

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
    
     
    val serverDetails = new Servers()        
    serverDetails.start
  
    credentials.foreach(  c => serverDetails !  ServerConnection(c) )
         
     
  }

}