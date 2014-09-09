package com.scala.bala.util

import scala.io.Source


object ResourceReader {

    /**
     * Reads configuration file.
     * @param fileName filename with path
     */
    def readResourceFile(fileName:String):Array[String]  = {
        val res = getClass.getResource(fileName)
        val source =  Source.fromURL(getClass.getResource(fileName))

        val lines = source.getLines.toArray
        source.close

        lines
    }

}
