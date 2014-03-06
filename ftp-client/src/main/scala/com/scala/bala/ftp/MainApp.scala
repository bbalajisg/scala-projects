package com.scala.bala.ftp

 
import com.scala.bala.ftp.util._
import java.util.regex.Pattern

object MainApp {

  def main(args: Array[String]): Unit = {
   
   /**
    * Call to read configuration file.
    */ 
     
    println("FTP Client started...");
    
    val client = new FTPClient(); 
    client.startClient
    
    println("FTP Client exited...");     
  }  

}