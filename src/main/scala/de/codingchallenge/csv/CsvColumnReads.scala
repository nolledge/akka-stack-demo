package de.codingchallenge.csv

/**
 * Typeclass for converting a sequence of columns into an Option of A
 * @tparam A the type we try to parse
 */
trait CsvColumnReads[A] {
  def read(columns: Seq[String]): Option[A]
}
