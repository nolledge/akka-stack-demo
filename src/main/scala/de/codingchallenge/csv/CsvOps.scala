package de.codingchallenge.csv

object CsvOps {

  implicit class CsvReader(row: String) {

    def csvToOptOf[A](delimiter: String)(implicit columnReader: CsvColumnReads[A]): Option[A] =
      columnReader.read(row.split(delimiter))

    def csvToOptOf[A](implicit reads: CsvColumnReads[A]): Option[A] =
      reads.read(row.split("\\|"))
  }

  implicit class CsvWriter[A](a: A) {
    def toCsvLine(delimiter: String)(implicit columnWriter: CsvColumnWrites[A]): String =
      columnWriter.write(a).mkString(delimiter)

    def toCsvLine(implicit columnWriter: CsvColumnWrites[A]): String =
      columnWriter.write(a).mkString("|")
  }

}
