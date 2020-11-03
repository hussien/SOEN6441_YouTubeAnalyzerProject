name := """youtube_video_search"""
organization := "com.upwork.tk"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  guice,
  "com.google.api-client" % "google-api-client" % "1.30.10",
  "com.google.oauth-client" % "google-oauth-client-jetty" % "1.31.1",
  "com.google.apis" % "google-api-services-youtube" % "v3-rev222-1.25.0",
  "org.apache.commons" % "commons-lang3" % "3.11"
)
