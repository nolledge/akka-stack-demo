package de.codingchallenge.repositories

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity.Chunked
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.ContentNegotiator.Alternative.ContentType
import akka.stream.scaladsl.{Flow, JavaFlowSupport, Source}
import akka.util.ByteString
import de.codingchallenge.models.ProductExport
import de.codingchallenge.csv.CsvOps._

import scala.concurrent.Future

class ProductExportRepository(actorSystem: ActorSystem) {

//  val headerLine = "produktId|name|beschreibung|preis|summeBestand"

  val baseUrl = "http://localhost:8080"

  def add(p: Source[ProductExport, _], articlesSize: Int): Future[HttpResponse] = {

    val entity = Chunked.fromData(
      ContentTypes.`text/csv(UTF-8)`,
        p.via(csvFlow))

    Http()(actorSystem)
      .singleRequest(
        HttpRequest(
          method = HttpMethods.PUT,
          uri = s"$baseUrl/products/$articlesSize",
          entity = entity))
  }

  private val csvFlow: Flow[ProductExport, ByteString, Any] =
    Flow.fromFunction(p => ByteString(p.toCsvLine + "\n"))
}
