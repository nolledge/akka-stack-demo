package de.codingchallenge.models

import de.codingchallenge.BaseSpec
import de.codingchallenge.csv.CsvOps._

class ArticleSpec extends BaseSpec {

  "The Article" must {
    "parse from sample" in {
      val sampleCsv = "A-UhnpVjCE|P-NhImbQSB|CKVTFO LCCOR TFIAZTP|lxqjlivf dppzKc|79.54|0"
      sampleCsv.csvToOptOf[Article].get mustBe Article(
        "A-UhnpVjCE",
        "P-NhImbQSB",
        "CKVTFO LCCOR TFIAZTP",
        "lxqjlivf dppzKc",
        79.54f,
        0)
    }
  }
}
