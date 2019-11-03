package com.baris.model

import cats.effect.IO
import org.http4s.circe.jsonOf
import io.circe.generic.auto._

case class Person(name: String, age: Int)

object Person {
  implicit val decoder = jsonOf[IO, Person]
}



