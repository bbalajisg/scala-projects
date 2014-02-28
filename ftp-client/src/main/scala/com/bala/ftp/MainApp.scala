package com.bala.ftp

object MainApp {

  def main(args: Array[String]): Unit = {
    
    println("FTP Client started...");
    
    val client = new FTPClient();
    client.startClient
    
    println("FTP Client exited...");
    
  } 

}