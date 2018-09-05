package de.codingchallenge.csv

/**
 * Typeclass for converting an a into a sequence of String
 * @tparam A the type we try to parse
 */
trait CsvColumnWrites[A] {
  def write(a: A): Seq[String]
}
