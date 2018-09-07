package de.codingchallenge.fixtures

import de.codingchallenge.models.Article

trait ArticleFixture {

  val articleUnavailable = Article(
    id = "id",
    productId = "productId",
    name = "name",
    description = Some("desc"),
    price = 1,
    stock = 0)

  val cheapestArticle = Article(
    id = "cheapest",
    productId = "productId",
    name = "cheapestArticle",
    description = Some("cheapestArticleDesc"),
    price = 0,
    stock = 1)

  val anotherArticle = articleUnavailable.copy(price = 1, stock = 5)

  val sampleData: Seq[String] =
    """A-U0xzQacF|P-U0xzQacF|EELDPYL||72.17|2
      A-CxzQacFC|P-U0xzQacF|LDPYLMEMU YH SZQJVDEAMG|mnlHhk|7.72|6
      A-0xzQacFC|P-U0xzQacF|LDPYLMEMU YH SZQJVDEAMG|mnlHhk|7.72|6
      A-6TAYX4Ac|P-U0xzQacF|QFBC ELDP||94.22|0
      A-tAYX4AcF|P-U0xzQacF|EELDPYL||72.17|2
      A-xzQacFC7|P-U0xzQacF|YLM|yuzjdAgfm wwfkgu|92.08|1
      A-TAYX4AcF|P-U0xzQacF|EELDPYL||72.17|2
      A-XzQacFC7|P-U0xzQacF|YLM|yuzjdAgfm wwfkgu|92.08|1
      A-AYX4AcFC|P-U0xzQacF|LDPYLMEMU YH SZQJVDEAMG|mnlHhk|7.72|6
      A-zQacFC7P|P-U0xzQacF|M|yuzjdAgfm wwfkgu|92.08|1
      A-YX4AcFC7|P-U0xzQacF|YLM|yuzjdAgfm wwfkgu|92.08|1
      A-qacFC7Pe|P-U0xzQacF|XYHUSZQJV|mtbswwf gusiukA wllbkxklo|21.62|4
      A-X4AcFC7P|P-U0xzQacF|M|yuzjdAgfm wwfkgu|92.08|1
      A-QacFC7Pe|P-U0xzQacF|XYHUSZQJV|mtbswwf gusiukA wllbkxklo|21.62|4
      A-4AcFC7Pe|P-4AcFC7Pe|XYHUSZQJV|mtbswwf gusiukA wllbkxklo|21.62|4
      A-acFC7Pey|P-4AcFC7Pe|SZQJVDEAMG FBMSNWLW FHKKG|siukAyzr bkxklohaa|66.59|3
      A-aHqBLPey|P-4AcFC7Pe|SZQJVDEAMG FBMSNWLW FHKKG|siukAyzr bkxklohaa|66.59|3
      A-AcFC7Pey|P-4AcFC7Pe|SZQJVDEAMG FBMSNWLW FHKKG|siukAyzr bkxklohaa|66.59|3
      A-hqBLPeyU|P-hqBLPeyU|QJVDEAM TFBMSNWLWH HKKGQURSZ|awtwllbkx ebH|16.03|22
      A-cFC7PeyU|P-cFC7PeyU|QJVDEAM TFBMSNWLWH HKKGQURSZ|awtwllbkx ebH|16.03|22
      A-HqBLPeyU|P-cFC7PeyU|QJVDEAM TFBMSNWLWH HKKGQURSZ|awtwllbkx ebH|16.03|22
      A-CFC7PeyU|P-cFC7PeyU|QJVDEAM TFBMSNWLWH HKKGQURSZ|awtwllbkx ebH|16.03|22
      A-qBLPeyUj|P-cFC7PeyU|EAMGTF MSNWLW|kgusiukAy llbkxkloha cplqlqxyJw yqscty XcpvAlqswj|15.66|1
      A-FC7PeyUj|P-FC7PeyUj|EAMGTF MSNWLW|kgusiukAy llbkxkloha cplqlqxyJw yqscty XcpvAlqswj|15.66|1
      A-QBLPeyUj|P-FC7PeyUj|EAMGTF MSNWLW|kgusiukAy llbkxkloha cplqlqxyJw yqscty XcpvAlqswj|15.66|1
      A-c7PeyUjD|P-FC7PeyUj|MGT|swwfkg rzDawtwl vvvjebHd aenb aXkcwJyqsc|77.34|17
      A-BLPeyUjD|P-FC7PeyUj|MGT|swwfkg rzDawtwl vvvjebHd aenb aXkcwJyqsc|77.34|17
      A-C7PeyUjD|P-C7PeyUjD|MGT|swwfkg rzDawtwl vvvjebHd aenb aXkcwJyqsc|77.34|17
      A-LPeyUjDg|P-C7PeyUjD|BMSNWL HFH||45.85|0
      A-7PeyUjDg|P-C7PeyUjD|BMSNWL HFH||45.85|0
      A-peyUjDgF|P-C7PeyUjD|SN L|hkqrz u wtwllbkxkl Hdwcpl|87.72|1
      A-DYMxSQVE|P-C7PeyUjD|GTFB|wwfkgu zDawtw|84.12|7
      A-PeyUjDgF|P-C7PeyUjD|SN L|hkqrz u wtwllbkxkl Hdwcpl|87.72|1
      A-YMxSQVEt|P-YMxSQVEt|MSNWLW FHKKG URSZIDUA|twllb v klohaa plqlqxyJw|8.03|37
      A-eyUjDgFM|P-eyUjDgFM|W|fkg rzDawtwl vvvjebHd|19.56|9
      A-MxSQVEtB|P-eyUjDgFM|NWLWHF KGQURSZ D||62.12|0
      A-2hqVEtBw|P-2hqVEtBw|HFH||45.85|0
      A-xSQVEtBw|P-2hqVEtBw|HFH||45.85|0
      A-yUjDgFMl|P-yUjDgFMl|FHKKG URSZIDUA|twllb v klohaa plqlqxyJw|8.03|37
      A-hqVEtBwW|P-hqVEtBwW|HKKGQURSZ D AKW||2.31|0
      A-YUjDgFMl|P-hqVEtBwW|FHKKG URSZIDUA|twllb v klohaa plqlqxyJw|8.03|37
      A-SQVEtBwW|P-hqVEtBwW|HKKGQURSZ D AKW||2.31|0
      A-UjDgFMlh|P-hqVEtBwW|GQURSZIDU|Ay llbkxkloha|95.28|1
      A-qVEtBwWF|P-hqVEtBwW|KGQURSZ D||62.12|0
      A-jDgFMlhq|P-hqVEtBwW|SZIDUA WA YWZLRLVBV||29.92|50
      A-QVEtBwWF|P-hqVEtBwW|KGQURSZ D||62.12|0
      A-dgFMlhqz|P-hqVEtBwW|UAKWATYWZ LVBVKVXJ LBOHHDAWAC|lqlqxyJwv PUpr XcpvAlqswj nlnzjmkv|24.45|2
      A-VEtBwWFK|P-hqVEtBwW|URSZIDUA WA YWZLRLVBV||29.92|50
      A-DgFMlhqz|P-hqVEtBwW|UAKWATYWZ LVBVKVXJ LBOHHDAWAC|lqlqxyJwv PUpr XcpvAlqswj nlnzjmkv|24.45|2
      A-EtBwWFKu|P-hqVEtBwW|ZIDU|Ay llbkxkloha|95.28|1
      A-gFMlhqza|P-hqVEtBwW|ATYWZ LVBVKVXJ|bHdwcplqlq kcwJyqsc rdXcpv W|88.45|7
      A-tBwWFKuS|P-hqVEtBwW|D||62.12|0
      A-fMlhqza2|P-fMlhqza2|WZLRLV VKVXJKEL|haaenbiaX|27.79|0
      A-bwWFKuSI|P-bwWFKuSI|AKW YWZLRLVBV||29.92|50
      A-FMlhqza2|P-FMlhqza2|WZLRLV VKVXJKEL|haaenbiaX|27.79|0
      A-BwWFKuSI|P-FMlhqza2|AKW YWZLRLVBV||29.92|50
      A-Mlhqza2Y|P-FMlhqza2|LRLV VKVXJKEL OHHDAW|plqlqxyJw yqscty XcpvAlqswj nlnzjmkv|24.45|2
      A-wWFKuSIU|P-wWFKuSIU|WA YWZLRLVBV VXJKELBOHH|aenb|10.51|10
      A-lhqza2Yr|P-lhqza2Yr|BVKVXJKELB HHDAWACEP|ql|48.6|10
      A-WWFKuSIU|P-lhqza2Yr|WA YWZLRLVBV VXJKELBOHH|aenb|10.51|10
      A-HHqza2Yr|P-lhqza2Yr|BVKVXJKELB HHDAWACEP|ql|48.6|10
      A-WFKuSIUA|P-lhqza2Yr|WZLRLV VKVXJKEL|haaenbiaX|27.79|0
      A-hqza2YrV|P-lhqza2Yr|K X|bHdwcplqlq kcwJyqsc rdXcpv W TqFbn|55.46|0
      A-FKuSIUAY|P-lhqza2Yr|LRLV VKVXJKEL OHHDAW|plqlqxyJw yqscty XcpvAlqswj nlnzjmkv|24.45|2
      A-Hqza2YrV|P-lhqza2Yr|K X|bHdwcplqlq kcwJyqsc rdXcpv W TqFbn|55.46|0
      A-KuSIUAYr|P-lhqza2Yr|BVKVXJKELB HHDAWACEP|ql|48.6|10
      A-qza2YrVV|P-lhqza2Yr|X|bHdwcplqlq kcwJyqsc rdXcpv W TqFbn|55.46|0
      A-uSIUAYrV|P-uSIUAYrV|K X|bHdwcplqlq kcwJyqsc rdXcpv W TqFbn|55.46|0
      A-rDwwLLBK|P-uSIUAYrV|JKELBO HDAWACEPNL|aXkcwJyqsc rdXcpv W|88.45|7
      A-sIUAYrVV|P-sIUAYrVV|X|bHdwcplqlq kcwJyqsc rdXcpv W TqFbn|55.46|0
      A-za2YrVVj|P-za2YrVVj|LBOHHDAWAC P|ql|48.6|10
      A-SIUAYrVV|P-za2YrVVj|X|bHdwcplqlq kcwJyqsc rdXcpv W TqFbn|55.46|0
      A-DwwLLBKX|P-za2YrVVj|LBOHHDAWAC P LBQ||27.63|8
      A-IUAYrVVj|P-za2YrVVj|LBOHHDAWAC P|ql|48.6|10
      A-a2YrVVjb|P-za2YrVVj|HDAWACEPNL QILAQX|cwJy PUpr XcpvAlqswj|15.66|1
      A-UAYrVVjb|P-za2YrVVj|HDAWACEPNL QILAQX|cwJy PUpr XcpvAlqswj|15.66|1
      A-wwLLBKXK|P-wwLLBKXK|BOHHDAWA EPNLBQILA|kcwJyqsc rdXcpv|97.51|0
      A-AYrVVjbd|P-AYrVVjbd|ACEPNLBQIL QXX||96.39|1
      A-2YrVVjbd|P-AYrVVjbd|ACEPNLBQIL QXX||96.39|1
      A-yrVVjbdc|P-AYrVVjbd|NLBQILAQX KYCJWWJVYS|UprdXcpvA yTqFbn wzijx vo|93.83|0
      A-wLLBKXKo|P-wLLBKXKo|DAWA||73.55|31
      A-YrVVjbdc|P-wLLBKXKo|NLBQILAQX KYCJWWJVYS|UprdXcpvA yTqFbn wzijx vo|93.83|0
      A-lLBKXKoH|P-lLBKXKoH|WACE NLBQILAQX|cwJy PUpr XcpvAlqswj|15.66|1
      A-rVVjbdcl|P-rVVjbdcl|LAQXXKYCJW||8.03|37
      A-LLBKXKoH|P-rVVjbdcl|WACE NLBQILAQX|cwJy PUpr XcpvAlqswj|15.66|1
      A-RVVjbdcl|P-rVVjbdcl|LAQXXKYCJW||8.03|37
      A-LBKXKoHA|P-rVVjbdcl|C P|ql|48.6|10
      A-VVjbdclQ|P-VVjbdclQ|A|kcwJyqsc rdXcpv|97.51|0
      A-BKXKoHAe|P-VVjbdclQ|LBQ LAQXXKYCJW J|PUpr XcpvAlqswj nlnzjmkv|24.45|2
      A-VjbdclQq|P-VVjbdclQ|KYCJWWJVYS|UprdXcpvA yTqFbn wzijx vo|93.83|0
      A-KXKoHAeN|P-VVjbdclQ|QILAQX KYCJWWJVYS PSUCP|d|31.34|35
      A-jbdclQqX|P-VVjbdclQ|CJ|v PUpr XcpvAlqswj nlnzjmkv|24.45|2
      A-XKoHAeNB|P-XKoHAeNB|LAQXXKYCJW J YSQPS||22.28|49
      A-JbdclQqX|P-XKoHAeNB|CJ|v PUpr XcpvAlqswj nlnzjmkv|24.45|2
      A-KoHAeNBa|P-XKoHAeNB|XKYCJWWJ YSQPS C|yydqpgW TqFbn wzijx vo gTro|12.86|2
      A-bdclQqXJ|P-XKoHAeNB|J YSQPS||22.28|49
      A-oHAeNBak|P-XKoHAeNB|JWWJVYSQPS C TRYDYX|pvAlqsw bnln ijxmdog roqumthtp lijqubnzz|97.32|24
      A-BdclQqXJ|P-BdclQqXJ|J YSQPS||22.28|49
      A-OHAeNBak|P-BdclQqXJ|JWWJVYSQPS C TRYDYX|pvAlqsw bnln ijxmdog roqumthtp lijqubnzz|97.32|24
      A-dclQqXJ7|P-BdclQqXJ|QPSU||77.34|17""".split("\n").map(_.trim)
}
