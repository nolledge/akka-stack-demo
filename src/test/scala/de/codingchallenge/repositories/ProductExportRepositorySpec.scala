package de.codingchallenge.repositories

import akka.http.scaladsl.model.StatusCodes
import akka.stream.scaladsl.{Sink, Source}
import de.codingchallenge.fixtures.{ArticleFixture, ProductExportFixture}
import de.codingchallenge.models.{Article, ProductExport}
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.{AkkaSpec, BaseSpec, models}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Honestly this is kind of an integration tests running against the Demo Server
 */
class ProductExportRepositorySpec extends BaseSpec with AkkaSpec with ProductExportFixture with ArticleFixture {

  "The ProductExportRepository" must {
    "put a single report" in {
      val repo = new ProductExportRepository(system)
      Await.result(repo.add(Source.single(productExport), 1), 2.second).status mustBe StatusCodes.OK
    }
    "put multiple reports" in {
      val repo = new ProductExportRepository(system)
      val articles = sampleData.map(_.csvToOptOf[Article].get).toList.map(e => models.ProductExport(e, 1))
      Await.result(repo.add(Source(articles), 1), 2.second).status mustBe StatusCodes.OK
    }
  }

}
