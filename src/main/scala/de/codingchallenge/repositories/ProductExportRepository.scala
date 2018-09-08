package de.codingchallenge.repositories

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.scaladsl.{Flow, Source}
import akka.stream.{ActorMaterializer, Materializer}
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.models.ProductExport

import scala.concurrent.{ExecutionContext, Future}

class ProductExportRepository(actorSystem: ActorSystem)(implicit ec: ExecutionContext) extends LazyLogging {

  private implicit val as: ActorSystem = actorSystem
  private implicit val mat: Materializer = ActorMaterializer()

  val headerLine = "produktId|name|beschreibung|preis|summeBestand"

  val baseUrl = "http://localhost:8080"

  def add(p: Source[ProductExport, NotUsed], articlesSize: Int): Future[HttpResponse] = {

    val sourceWithHeader = p.via(csvFlow)
      .prepend(Source.single(headerLine))
      .intersperse("\n")
      .map(ByteString.apply)

    /**
     * Setting charset to utf8 results in HTTP.406
     */
    Http()
      .singleRequest(
        HttpRequest(
          method = HttpMethods.PUT,
          uri = s"$baseUrl/products/$articlesSize",
          entity = HttpEntity(ContentType.WithMissingCharset(MediaTypes.`text/csv`), sourceWithHeader)
        ))
      .map { res =>
        logger.info(s"Server responded with $res")
        res
      }
  }

  private val csvFlow: Flow[ProductExport, String, NotUsed] =
    Flow.fromFunction { p =>
      logger.info(s"streaming export record $p")
      p.toCsvLine
    }

}
