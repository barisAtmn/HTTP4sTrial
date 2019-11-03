package com.baris

import cats.data.Kleisli
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import org.http4s.{HttpRoutes, Request, Response}
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder

object ApplicationIO extends IOApp{

  val helloWorldService : Kleisli[IO, Request[IO], Response[IO]]= HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }.orNotFound


  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8082, "localhost")
      .withHttpApp(helloWorldService) // Kleisli[F, Request[G], Response[G]]
      .resource
      .use(_ => IO.never) // Allocates a resource and supplies it to the given function.
      .as(ExitCode.Success)
}
