package de.codingchallenge.services

import akka.NotUsed
import akka.http.scaladsl.model.HttpResponse
import akka.stream.scaladsl.{Sink, Source}
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.fixtures.{ArticleFixture, ProductExportFixture}
import de.codingchallenge.models.{Article, ProductExport}
import de.codingchallenge.repositories.{ArticleRepository, ProductExportRepository}
import de.codingchallenge.{AkkaSpec, BaseSpec}
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ArticleExportServiceSpec extends BaseSpec with AkkaSpec {

  trait TestSetup extends ProductExportFixture with ArticleFixture {
    val productsSize = 10
    val articleRepositoryMock = mock[ArticleRepository]
    val productExportRepositoryMock = mock[ProductExportRepository]
    val service =
      new ArticleExportService(articleRepositoryMock, productExportRepositoryMock, materializer)
  }

  "The ArticleExportService" must {
    "ignore unavailable articles" in new TestSetup {
      doReturn(Source(List(articleUnavailable)), Nil: _*)
        .when(articleRepositoryMock)
        .getArticles(any())
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(productsSize), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] =
        ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      Await.result(sourceCaptor.getValue.runWith(Sink.headOption), 1.second) mustBe None
    }
    "pass the expected source to the product export repository" in new TestSetup {
      doReturn(Source(List(articleUnavailable, cheapestArticle, anotherArticle)), Nil: _*)
        .when(articleRepositoryMock)
        .getArticles(any())
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(productsSize), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] =
        ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      Await.result(sourceCaptor.getValue.runWith(Sink.head), 1.second) mustBe productExport
    }
    "contain only one entry per product" in new TestSetup with ArticleFixture {

      val articles = sampleData.map(_.csvToOptOf[Article].get).toList
      doReturn(Source(articles), Nil: _*)
        .when(articleRepositoryMock)
        .getArticles(any())
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(productsSize), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] =
        ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      val results = Await.result(sourceCaptor.getValue.runWith(Sink.seq), 1.second)
      results.foreach { r =>
        results.count(_.productId == r.productId) == 1
      }
    }
    "find the cheapest element of a group" in new TestSetup with ArticleFixture {

      val articles = sampleGroup.map(_.csvToOptOf[Article].get).toList
      doReturn(Source(articles), Nil: _*)
        .when(articleRepositoryMock)
        .getArticles(any())
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(productsSize), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] =
        ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      val results = Await.result(sourceCaptor.getValue.runWith(Sink.seq), 1.second)
      results.head mustBe ProductExport(
        productId = "P-C7PeyUjD",
        name = "MGT",
        description = "swwfkg rzDawtwl vvvjebHd aenb aXkcwJyqsc",
        price = 77.34f,
        stockSum = 26
      )
    }
  }
}
