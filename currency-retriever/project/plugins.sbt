logLevel := Level.Warn

resolvers ++= Seq("typesafe" at "http://repo.typesafe.com/typesafe/releases/")

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.2") //just added 2.2.2 instead of 2.2.1