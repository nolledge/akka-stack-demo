package de.codingchallenge.models

import java.text.DecimalFormat
import java.util.Locale

import de.codingchallenge.csv.CsvColumnWrites

/**
 * Data structure representing the CSV export
 * @param productId product group identifier
 * @param name name of the product
 * @param description description of the product
 * @param price price
 * @param stockSum stock over group
 */
case class ProductExport(productId: String, name: String, description: String, price:Float, stockSum: Int)

object ProductExport {


  implicit val columnWrites: CsvColumnWrites[ProductExport] = (p: ProductExport) => Seq(
    p.productId, p.name, p.description,"%.2f".formatLocal(java.util.Locale.US, p.price), p.stockSum.toString
  )


  def apply(a: Article, stockSum: Int): ProductExport  = new ProductExport(
    productId = a.productId,
    name = a.name,
    description = a.description.getOrElse(""),
    price = a.price,
    stockSum = stockSum
  )
}
