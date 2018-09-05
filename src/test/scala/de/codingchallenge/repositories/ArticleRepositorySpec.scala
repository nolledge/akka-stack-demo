package de.codingchallenge.repositories

import akka.stream.scaladsl.Sink
import de.codingchallenge.models.Article
import de.codingchallenge.{AkkaSpec, BaseSpec}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}

import scala.concurrent.duration._
import scala.concurrent.Await

class ArticleRepositorySpec extends BaseSpec with AkkaSpec {

  "The ArticleRepository" must {
    "parse articles as expected" in {
      val repo = new ArticleRepository(system)
      Await.result(repo.getArticles(1).runWith(Sink.head), 1.second) mustBe a[Article]
    }
  }

}