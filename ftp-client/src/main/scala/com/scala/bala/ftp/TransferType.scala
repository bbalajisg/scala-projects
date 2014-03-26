package com.scala.bala.ftp

object TransferType extends Enumeration {
	
	type TransferType = Value
			
	val ASCII, BINARY, EBCDIC, LOCAL = Value
}