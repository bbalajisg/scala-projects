package com.scala.bala.ftp

import com.scala.bala.ftp.util.FTPConfigurationReader
import akka.actor.{Props, ActorSystem}
import akka.routing.RoundRobinRouter

object MainApp {

  def main(args: Array[String]): Unit = {

    /**
     * Call to read configuration file.
     */

    println("FTP Client started...")

    val serverIps = FTPConfigurationReader.severIPs
    val userName = FTPConfigurationReader.userNames
    val password = FTPConfigurationReader.passwords

    val zippedCredentials = (serverIps zip userName) zip password
    val credentials =  zippedCredentials map {
        case ((a,b),c) => Array(a.trim(),b.trim(),c.trim())
    }

    val system = ActorSystem()
    val server = system.actorOf(Props(new Servers).withRouter(RoundRobinRouter(nrOfInstances = 10)), "Servers")

    credentials.foreach(server ! ServerConnection.connect(_) )

  }


}