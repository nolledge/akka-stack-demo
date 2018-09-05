package de.codingchallenge.fixtures

import de.codingchallenge.models.Article

trait ArticleFixture {

  val articleUnavailable = Article(
    id = "id",
    productId = "productId",
    name = "name",
    description = "desc",
    price = 1,
    stock = 0)

  val cheapestArticle = Article(
    id = "cheapest",
    productId = "productId",
    name = "cheapestArticle",
    description = "cheapestArticleDesc",
    price = 0,
    stock = 1)

  val anotherArticle = articleUnavailable.copy(price = 1, stock = 5)
}
