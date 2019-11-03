package com.baris.Service

import cats.effect.IO
import com.baris.model.Person
import org.http4s.HttpRoutes
import org.http4s.dsl.impl.IntVar
import org.http4s.dsl.io.{->, /, GET, Ok, Root}
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl.io._

object PersonService {

  /**
  Returning content in the response
   In order to return content of type T in the response an EntityEncoder[T] must be used. We can define the EntityEncoder[T]
   implictly so that it doesn’t need to be explicitly included when serving the response.
    Ok(getTweet(tweetId))(tweetEncoder)
    */

  def getPerson(age: Int): IO[Person] = IO{Person("B",1)}

  val personService = HttpRoutes.of[IO] {
    case GET -> Root / "person" / IntVar(age) =>
      getPerson(age).flatMap(person => Ok(person.asJson))
//    case req @ POST -> Root / "person" =>
//      req.as[Person].flatMap(Ok(Person("Baris",1).asJson))
  }

}
