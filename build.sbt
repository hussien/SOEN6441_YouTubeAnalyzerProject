name := """youtube_video_search"""
organization := "Folks"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"
routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  ws,
  "org.webjars" %% "webjars-play" % "2.8.0",
  "org.webjars" % "bootstrap" % "2.3.2",
  "org.webjars" % "flot" % "0.8.3",
  // Testing libraries for dealing with CompletionStage...
  "org.assertj" % "assertj-core" % "3.14.0" % Test,
  "org.awaitility" % "awaitility" % "4.0.1" % Test,
  guice,
  "com.google.api-client" % "google-api-client" % "1.30.10",
  "com.google.oauth-client" % "google-oauth-client-jetty" % "1.31.1",
  "com.google.apis" % "google-api-services-youtube" % "v3-rev222-1.25.0",
  "org.apache.commons" % "commons-lang3" % "3.11"
)
