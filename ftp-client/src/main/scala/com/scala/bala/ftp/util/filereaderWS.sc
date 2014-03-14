package com.scala.bala.ftp.util

object filereaderWS {
  
  	 	val pasv = "227 Entering Passive Mode (10,5,3,3,71,198)"
                                                  //> pasv  : String = 227 Entering Passive Mode (10,5,3,3,71,198)
		
		 
		val pattern = """.*?(\d+),(\d+),(\d+),(\d+),(\d+),(\d+).*?""".r
                                                  //> pattern  : scala.util.matching.Regex = .*?(\d+),(\d+),(\d+),(\d+),(\d+),(\d+
                                                  //| ).*?
		   
		      
		val pattern(d0,d1,d2,d3,d4,d5) = pasv
                                                  //> d0  : String = 10
                                                  //| d1  : String = 5
                                                  //| d2  : String = 3
                                                  //| d3  : String = 3
                                                  //| d4  : String = 71
                                                  //| d5  : String = 198
		  
	 
	
	  
	  
	  val filesToRead = FTPConfigurationReader.getConfiguration("SERVER_FILES_TO_READ")
                                                  //> Reading configuration file....
                                                  //| java.lang.ExceptionInInitializerError
                                                  //| 	at com.scala.bala.ftp.util.filereaderWS$$anonfun$main$1.apply$mcV$sp(com
                                                  //| .scala.bala.ftp.util.filereaderWS.scala:17)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at com.scala.bala.ftp.util.filereaderWS$.main(com.scala.bala.ftp.util.fi
                                                  //| lereaderWS.scala:3)
                                                  //| 	at com.scala.bala.ftp.util.filereaderWS.main(com.scala.bala.ftp.util.fil
                                                  //| ereaderWS.scala)
                                                  //| Caused by: java.io.FileNotFoundException: bin\config\ftp.properties (The sys
                                                  //| tem cannot find the path specified)
                                                  //| 	at java.io.FileInputStream.open(Native Method)
                                                  //| 	at java.io.FileInputStream.<init>(Unknown Source)
                                                  //| 	at scala.
                                                  //| Output exceeds cutoff limit.
  
	
}