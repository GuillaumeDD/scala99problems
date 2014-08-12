name := "ScalaGraph"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.1"

libraryDependencies += "ch.qos.logback" % "logback-core" % "1.0.9"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9"
