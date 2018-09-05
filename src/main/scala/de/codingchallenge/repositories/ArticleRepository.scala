package de.codingchallenge.repositories

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, Uri}
import akka.stream.scaladsl.{Flow, Framing, Source}
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.models.Article


class ArticleRepository(actorSystem: ActorSystem) extends LazyLogging{

  implicit val as = actorSystem
  // might be a good idea to make that value configurable
  val baseUrl: String = "http://localhost:8080"

  lazy val connection = Http().superPool[NotUsed]()

  def getArticles(limit: Int): Source[Article, _] = Source.fromFuture(
    Http()
    .singleRequest(HttpRequest(uri = s"$baseUrl/articles/$limit"))
  ).flatMapConcat(res =>
    res.entity
        .dataBytes
        .via(lineDelimiter)
        .via(articleFlow))

  val lineDelimiter: Flow[ByteString, ByteString, NotUsed] =
    Framing.delimiter(ByteString("\n"), 128, allowTruncation = true)

  val articleFlow: Flow[ByteString, Article, NotUsed] = Flow[ByteString]
    .map(_.utf8String)
    .drop(1)
    .map{e => logger.info(s"CSV string: $e"); e}
    .map(_.csvToOptOf[Article])
    .map{
      case Some(a) => a
      case _ => throw new IllegalStateException("Malformed CSV data aborting stream")
    }
}


