package de.codingchallenge.models

import de.codingchallenge.BaseSpec
import de.codingchallenge.csv.CsvOps._
import de.codingchallenge.fixtures.ArticleFixture

class ArticleSpec extends BaseSpec {

  "The Article" must {
    "parse fields as expecte" in {
      val sampleCsv = "A-UhnpVjCE|P-NhImbQSB|CKVTFO LCCOR TFIAZTP|lxqjlivf dppzKc|79.54|0"
      sampleCsv.csvToOptOf[Article].get mustBe Article(
        "A-UhnpVjCE",
        "P-NhImbQSB",
        "CKVTFO LCCOR TFIAZTP",
        Some("lxqjlivf dppzKc"),
        79.54f,
        0)
    }
    "parse a sample data" in new ArticleFixture {
      sampleData
        .foreach(row => {
          row.csvToOptOf[Article].get mustBe a[Article]
        }
        )
    }
  }
}
