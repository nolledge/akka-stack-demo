package de.codingchallenge.repositories

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.scaladsl.Source
import de.codingchallenge.models.Article

class ArticleRepository(implicit as: ActorSystem){

  def getArticles(limit: Int): Source[Article, NotUsed] = Http().re
}
