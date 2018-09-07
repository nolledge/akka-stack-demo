package de.codingchallenge.repositories

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity.Chunked
import akka.http.scaladsl.model._
import akka.stream.scaladsl.{Flow, Source}
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.models.ProductExport

import scala.concurrent.{ExecutionContext, Future}

class ProductExportRepository(actorSystem: ActorSystem)(implicit ec: ExecutionContext) extends LazyLogging {

  private implicit val as: ActorSystem = actorSystem

  val headerLine = "produktId|name|beschreibung|preis|summeBestand"

  val baseUrl = "http://localhost:8080"

  def add(p: Source[ProductExport, NotUsed], articlesSize: Int): Future[HttpResponse] = {

    val sourceWithHeader = p.via(csvFlow)
      .prepend(Source.single(headerLine))
      .intersperse("\n")
      .map(ByteString.apply)

    // it did not work with charset information
    val entity = Chunked.fromData(
      ContentType.WithMissingCharset(MediaTypes.`text/csv`),
      sourceWithHeader
    )

    Http()
      .singleRequest(
        HttpRequest(
          method = HttpMethods.PUT,
          uri = s"$baseUrl/products/$articlesSize",
          entity = entity))
      .map{res =>
        logger.info(s"Server responded with $res")
        res
      }
  }

  private val csvFlow: Flow[ProductExport, String, NotUsed] =
    Flow.fromFunction{p =>
      logger.info(s"processing export record $p")
      p.toCsvLine
    }

}
