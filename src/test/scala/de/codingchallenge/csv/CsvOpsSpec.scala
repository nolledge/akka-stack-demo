package de.codingchallenge.csv

import org.scalatest.{MustMatchers, WordSpec}
import CsvOps._

import scala.util.{Success, Try}

class CsvOpsSpec extends WordSpec with MustMatchers{

  case class CsvTestData(s: String, d: Double)
  implicit val csvReads: CsvColumnReads[CsvTestData] = (columns: Seq[String]) => {
    Try{(columns.head, columns(1).toDouble)} match{
      case Success((s: String, d: Double)) => Some(CsvTestData(s,d))
      case _ => None
    }
  }

  implicit val csvWrites: CsvColumnWrites[CsvTestData] = (csvTestData: CsvTestData) =>
    csvTestData.productIterator.map(_.toString).toSeq

  "The CsvOps reading operations" should {
    "parse a csv line with the default delimiter as expected" in {
     val csvLine = "myStringVal|3.0"
      csvLine.csvToOptOf[CsvTestData] mustBe Some(CsvTestData("myStringVal", 3d))
    }
    "parse a csv line with expected delimiter" in {
      val csvLine = "myStringVal,3.0"
      csvLine.csvToOptOf[CsvTestData](",") mustBe Some(CsvTestData("myStringVal", 3d))
    }
  }
  "The CsvOps writing operations" should {
    "write a csv line with default delimiter" in {
      val expectedLine = "myStringVal|3.0"
      val testData = CsvTestData("myStringVal", 3)
      testData.toCsvLine mustBe expectedLine
    }
    "write a csv line with expected delimiter" in {
      val expectedCsvLine = "myStringVal,3.0"
      val testData = CsvTestData("myStringVal", 3)
      testData.toCsvLine(",") mustBe expectedCsvLine
    }
  }
}
