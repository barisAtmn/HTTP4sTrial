# HTTP4S(A minimal, idiomatic Scala interface for HTTP)

Typeful, functional, streaming HTTP for Scala.

- Typeful
http4s servers and clients share an immutable model of requests and responses. Standard headers are modeled as semantic types, and entity codecs are done by typeclass.

- Functional
The pure functional side of Scala is favored to promote composability and easy reasoning about your code. I/O is managed through cats-effect.

- Streaming
http4s is built on FS2, a streaming library that provides for processing and emitting large payloads in constant space and implementing websockets.

