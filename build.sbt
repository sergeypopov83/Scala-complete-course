import _root_.sbt.Keys._

name := "scala-course"

version := "1.0"

scalaVersion := "2.12.2"

scalacOptions := Seq(
  "-encoding", "utf8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8",
  "-Ymacro-debug-lite",
  "-language:experimental.macros")

resolvers ++= Seq(
  Resolver.jcenterRepo,
  Resolver.url("jCenter", url("http://jcenter.bintray.com/")),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"))

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.7"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")

libraryDependencies += "org.scala-lang" % "scala-library" % "2.12.2"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.2"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.0-MF"

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.7.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "de.sciss" %% "coroutines-common" % "0.1.0"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"

libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.5.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.3" % Test,
  "com.typesafe.akka" %% "akka-remote" % "2.5.3",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.3"
)

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"

libraryDependencies += "com.softwaremill.macwire" %% "macrosakka" % "2.3.0" % "provided"

libraryDependencies += "com.softwaremill.macwire" %% "util" % "2.3.0"

libraryDependencies += "com.softwaremill.macwire" %% "proxy" % "2.3.0"

libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.9"

parallelExecution in ThisBuild := false