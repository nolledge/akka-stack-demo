package de.codingchallenge.repositories

import akka.http.scaladsl.model.StatusCodes
import akka.stream.scaladsl.{Sink, Source}
import de.codingchallenge.fixtures.ProductExportFixture
import de.codingchallenge.models.Article
import de.codingchallenge.{AkkaSpec, BaseSpec}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Honestly this is kind of an integration tests running against the Demo Server
 */
class ProductExportRepositorySpec extends BaseSpec with AkkaSpec with ProductExportFixture {

  "The ProductExportRepository" must {
    "put product reports as expected" in {
      val repo = new ProductExportRepository(system)
      Await.result(repo.add(Source.single(productExport), 1), 2.second).status mustBe StatusCodes.OK
    }
  }

}
