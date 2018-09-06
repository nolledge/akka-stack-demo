package de.codingchallenge.repositories

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity.Chunked
import akka.http.scaladsl.model._
import akka.stream.scaladsl.{Flow, Source}
import akka.util.ByteString
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.models.ProductExport

import scala.concurrent.Future

class ProductExportRepository(actorSystem: ActorSystem) {

  val headerLine = "produktId|name|beschreibung|preis|summeBestand\n"

  val baseUrl = "http://localhost:8080"

  def add(p: Source[ProductExport, _], articlesSize: Int): Future[HttpResponse] = {

    // it did not work with charset information
    val entity = Chunked.fromData(
      ContentType.WithMissingCharset(MediaTypes.`text/csv`),
      p.via(csvFlow)
        .prepend(Source.single(ByteString(headerLine)))
    )

    Http()(actorSystem)
      .singleRequest(
        HttpRequest(
          method = HttpMethods.PUT,
          uri = s"$baseUrl/products/$articlesSize",
          entity = entity))
  }

  private val csvFlow: Flow[ProductExport, ByteString, Any] =
    Flow.fromFunction(p => ByteString(p.toCsvLine))

}
