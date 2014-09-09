name := "currency-retriever"

version := "1.0"


scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.7" % "test",
  "joda-time" % "joda-time" % "2.4",
  "org.joda" % "joda-convert" % "1.2",
  "org.scalatest" % "scalatest_2.10" % "2.2.1",
  "com.typesafe.play" %% "play" % "2.2.2",
  "io.argonaut" % "argonaut-unfiltered_2.10" % "6.0.4"
)

mainClass := Some("com.scala.bala.MainApp")
