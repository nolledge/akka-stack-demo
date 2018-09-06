package de.codingchallenge.services

import akka.NotUsed
import akka.http.scaladsl.model.HttpResponse
import akka.stream.Materializer
import akka.stream.contrib.AccumulateWhileUnchanged
import akka.stream.scaladsl.{Sink, Source}
import com.typesafe.scalalogging.LazyLogging
import de.codingchallenge.models.{Article, ProductExport}
import de.codingchallenge.repositories.{ArticleRepository, ProductExportRepository}

import scala.concurrent.Future

class ArticleExportService(
  articleRepository: ArticleRepository,
  productExportRepository: ProductExportRepository,
  mat: Materializer
) extends LazyLogging {
  implicit val m: Materializer = mat

  val productsSize: Int = 100

  def exportArticles(): Future[HttpResponse] = productExportRepository.add(Source.fromGraph(
    articleRepository
      .getArticles(productsSize)
      .filter(_.stock > 0)
      .map(a => (a, a.stock))
      .via(new AccumulateWhileUnchanged[(Article, Int), String](_._1.productId))
      .map(_.reduce[(Article, Int)] {
        case ((a1, c1), (a2, c2)) if a1.price < a2.price => (a1, c1 + c2)
        case ((a1, c1), (a2, c2)) if a1.price > a2.price => (a2, c1 + c2)
        case ((a1, c1), (_, c2)) => (a1, c1 + c2)
      })
      .map { case (article, stockSum) =>
        logger.info(s"Reduced to article: $article and stockSum: $stockSum")
        ProductExport(article, stockSum) }
  ), productsSize)

}
