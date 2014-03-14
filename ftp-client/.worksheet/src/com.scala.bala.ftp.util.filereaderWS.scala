package com.scala.bala.ftp.util

object filereaderWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(119); 
  
  	 	val pasv = "227 Entering Passive Mode (10,5,3,3,71,198)";System.out.println("""pasv  : String = """ + $show(pasv ));$skip(73); 
		
		 
		val pattern = """.*?(\d+),(\d+),(\d+),(\d+),(\d+),(\d+).*?""".r;System.out.println("""pattern  : scala.util.matching.Regex = """ + $show(pattern ));$skip(55); 
		   
		      
		val pattern(d0,d1,d2,d3,d4,d5) = pasv;System.out.println("""d0  : String = """ + $show(d0 ));System.out.println("""d1  : String = """ + $show(d1 ));System.out.println("""d2  : String = """ + $show(d2 ));System.out.println("""d3  : String = """ + $show(d3 ));System.out.println("""d4  : String = """ + $show(d4 ));System.out.println("""d5  : String = """ + $show(d5 ));$skip(103); 
		  
	 
	
	  
	  
	  val filesToRead = FTPConfigurationReader.getConfiguration("SERVER_FILES_TO_READ");System.out.println("""filesToRead  : String = """ + $show(filesToRead ))}
  
	
}
