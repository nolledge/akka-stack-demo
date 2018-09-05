package de.codingchallenge.models

import de.codingchallenge.csv.CsvColumnWrites

case class ProductExport(productId: String, name: String, description: String, price:Float, stockSum: Int)

object ProductExport{

  implicit val columnWrites: CsvColumnWrites[ProductExport] = (p: ProductExport) =>
    p.productIterator.map(_.toString).toSeq

  def apply(a: Article, stockSum: Int): ProductExport  = new ProductExport(
    productId = a.productId,
    name = a.name,
    description = a.description,
    price = a.price,
    stockSum = stockSum
  )
}
