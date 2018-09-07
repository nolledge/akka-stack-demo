package de.codingchallenge.models

import de.codingchallenge.csv.CsvColumnReads

import scala.util.{Success, Try}

/**
 * Represents a record of the article list
 * @param id identifier of the article
 * @param productId identifier of the product the article is a variant of
 * @param description some descriptive text
 * @param price the articles price
 * @param stock the current stock
 */
case class Article(id: String, productId: String, name: String, description: Option[String], price: Float, stock: Int)

object Article {

  /**
   * Reads an article from a sequence of columns. Returns an Option with article in success case
   */
  implicit val csvColumnReads: CsvColumnReads[Article] = (s: Seq[String]) =>
    Try{ (s.head, s(1), s(2), Try(s(3)).toOption, s(4).toFloat, s(5).toInt) } match {
      case Success(t) => Some((Article.apply _).tupled(t))
      case _ => None
    }

}
