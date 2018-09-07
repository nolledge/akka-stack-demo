package de.codingchallenge.services

import akka.NotUsed
import akka.http.scaladsl.model.HttpResponse
import akka.stream.scaladsl.{Sink, Source}
import de.codingchallenge.fixtures.{ArticleFixture, ProductExportFixture}
import de.codingchallenge.models.{Article, ProductExport}
import de.codingchallenge.repositories.{ArticleRepository, ProductExportRepository}
import de.codingchallenge.{AkkaSpec, BaseSpec}
import org.mockito.ArgumentCaptor

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import org.mockito.internal.verification.argumentmatching.ArgumentMatchingTool
import de.codingchallenge.csv.CsvOps._

class ArticleExportServiceSpec extends BaseSpec with AkkaSpec{

  trait TestSetup extends ProductExportFixture with ArticleFixture{
    val articleRepositoryMock = mock[ArticleRepository]
    val productExportRepositoryMock = mock[ProductExportRepository]
    val service = new ArticleExportService(articleRepositoryMock, productExportRepositoryMock, materializer)
  }

  "The ArticleExportService" must {
    "ignore unavailable articles" in new TestSetup{
      doReturn(Source(List(articleUnavailable)), Nil: _*)
        .when(articleRepositoryMock)
        .getArticles(100)
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] = ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      Await.result(sourceCaptor.getValue.runWith(Sink.headOption), 1.second) mustBe None
    }
    "pass the expected source to the product export repository" in new TestSetup{
       doReturn(Source(List(articleUnavailable, cheapestArticle, anotherArticle)), Nil: _*)
       .when(articleRepositoryMock)
         .getArticles(100)
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] = ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      Await.result(sourceCaptor.getValue.runWith(Sink.head), 1.second) mustBe productExport
    }
    "pass sample data as expected" in new TestSetup with ArticleFixture {

      val articles = sampleData.map(_.csvToOptOf[Article].get).toList
      doReturn(Source(articles), Nil: _*)
        .when(articleRepositoryMock)
        .getArticles(100)
      when(productExportRepositoryMock.add(any(), any()))
        .thenReturn(Future.successful(HttpResponse()))

      Await.result(service.exportArticles(), 5.second)
      val sourceCaptor: ArgumentCaptor[Source[ProductExport, NotUsed]] = ArgumentCaptor.forClass(classOf[Source[ProductExport, NotUsed]])
      verify(productExportRepositoryMock).add(sourceCaptor.capture(), any())
      Await.result(sourceCaptor.getValue.runWith(Sink.seq), 1.second).foreach { e =>
        e mustBe a[ProductExport]
      }
    }
  }
}
