import sbt._

object Dependencies {

  val Http4sVersion = "0.21.0-M5"
  val CirceVersion = "0.12.3"
  val Specs2Version = "4.8.0"
  val LogbackVersion = "1.2.3"

  lazy val http4s = Seq(

    "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s"      %% "http4s-blaze-client" % Http4sVersion,
    "org.http4s"      %% "http4s-circe"        % Http4sVersion,
    "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  )

  lazy val circe = Seq(
    "io.circe"        %% "circe-generic"       % CirceVersion,
  )

  lazy val testLib = Seq(
    "org.specs2"      %% "specs2-core"         % Specs2Version % "test"
  )

  lazy val logging = Seq(
    "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
  )

}
