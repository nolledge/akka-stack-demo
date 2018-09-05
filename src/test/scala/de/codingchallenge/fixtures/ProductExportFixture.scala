package de.codingchallenge.fixtures

import de.codingchallenge.models.{Article, ProductExport}

trait ProductExportFixture {

  val productExport = ProductExport(
    productId = "productId",
    name = "cheapestArticle",
    description = "cheapestArticleDesc",
    price = 0,
    stockSum = 6
  )
}
