package de.codingchallenge

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.{pathPrefix, _}
import akka.http.scaladsl.server._
import akka.stream.{ActorMaterializer, Materializer}
import akka.util.ByteString
import de.codingchallenge.configuration.Environment
import com.typesafe.scalalogging.LazyLogging
import de.codingchallenge.services.ArticleExportService

class Routes(articleExportService: ArticleExportService,
             actorSystem: ActorSystem,
             actorMaterializer: ActorMaterializer,
             environment: Environment)
  extends LazyLogging {

  implicit val as: ActorSystem = actorSystem
  implicit val mat: Materializer = actorMaterializer

  var on: Boolean = true


  val serviceRoutes: Route =
    pathSuffix("readinessProbe") {
      get {
        if(on) {
          complete {
            HttpEntity(ContentTypes.`application/json`, ByteString(de.codingchallenge.BuildInfo.toJson))
          }
        } else {
          complete(StatusCodes.ServiceUnavailable)
        }
      }
    } ~ path("health") {
      get {
        complete {
          HttpEntity(ContentTypes.`application/json`, ByteString(de.codingchallenge.BuildInfo.toJson))
        }
      }
    }

  val exportRoute: Route =
      path("articles") {
        get {
          onSuccess(articleExportService.exportArticles()) { res =>
            complete(res)
          }
        }
      }

  val routes: Route =
      pathPrefix("inoio-coding-challenge") {
        serviceRoutes
      } ~ pathPrefix("inoio-coding-challenge" / "export") {
         exportRoute
      } ~ serviceRoutes

  val rejectRoute: Route = reject

  def shutdown(): Unit = on = false

}
