package com.baris

import cats.effect._
import org.http4s.dsl.io._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import scala.concurrent.ExecutionContext.Implicits.global
import cats.effect.{IO}
import cats.syntax.semigroupk._
import org.http4s.HttpRoutes
import org.http4s.syntax.kleisli._
import com.baris.Service.PersonService._

/**
  You also will need a ContextShift and a Timer. These come for free if you are in an IOApp
  */
object Application extends App{

  implicit val cs: ContextShift[IO] = IO.contextShift(global)
  implicit val timer: Timer[IO] = IO.timer(global)

  /**
   Using the http4s-dsl, we can construct an HttpRoutes by pattern matching the request.
   helloWorldService: org.http4s.HttpRoutes[cats.effect.IO] = Kleisli(org.http4s.HttpRoutes$$$Lambda$20634/631713210@753dc50c)
   of ==> Lifts a partial function into an [[HttpRoutes]]
    */
  val helloWorldService : HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }

  /**
     Running your service : http4s supports multiple server backends. Blaze the native backend supported by http4s.
     We start from a BlazeServerBuilder, and then mount the helloWorldService under the base path of / and the remainder of the services
     under the base path of /api.
    */


  /**
     Multiple HttpRoutes can be combined with the combineK method (or its alias <+>) by importing cats.implicits._ and org.http4s.implicits._.
     Please ensure partial unification is enabled in your build.sbt.
     scalacOptions ++= Seq("-Ypartial-unification")
    */


  val services = personService <+> helloWorldService

  val httpApp = Router("/" -> helloWorldService, "/api" -> services).orNotFound

  val serverBuilder = BlazeServerBuilder[IO].bindHttp(8083, "localhost").withHttpApp(httpApp)

  val fiber = serverBuilder.resource.use(_ => IO.never).start.unsafeRunSync()


}
