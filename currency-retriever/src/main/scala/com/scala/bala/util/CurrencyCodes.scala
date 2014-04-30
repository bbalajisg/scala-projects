package com.scala.bala.util

object CurrencyCodes {

  /**
   * Reads the configured currency from configuration
   */
  private val reader =  ResourceReader.readResourceFile("/currency-pairs.properties")
  /**
   * Holds an currency codes collection
   * Todo
   */
  val currencyPairs:Array[String] = reader.map( _.replace("/",""))
  /**
   * return example USD/SGD
   */
  val currencyAgainst:Array[String] = reader;

}
