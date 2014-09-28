sbtPlugin := true

name := "sbt-azurepack"

organization := "io.kostrse"

version := "0.1-SNAPSHOT"

description := "sbt plugin to create Azure deployment packages."

licenses := Seq("MIT License" -> url("https://github.com/kostrse/sbt-azurepack/blob/master/LICENSE"))

scalaVersion := "2.10.3"

scalacOptions := Seq("-deprecation", "-unchecked")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test"
