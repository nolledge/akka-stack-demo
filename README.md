## Coding Challenge

Als Marketingbeauftragter möchte ich die aktuelle Produktliste unseres 
Online-Shops an einen externen Dienstleister weiterleiten.

Ausgangsbasis ist die aktuelle Liste von Artikeln im CSV Format ("Artikel"/"Produkt": ein Produkt
enthält mehrere Artikel, auch Varianten genannt). Alle Artikel eines Produktes
folgen aufeinander.

Wenn kein Artikel eines Produktes einen Bestand > 0 hat, soll für dieses Produkt
kein Eintrag im Ergebnis vorhanden sein.

Pro Produkt soll jeweils der günstigste Artikel mit Bestand > 0 im Ergebnis enthalten
sein (mit seinen jeweiligen Eigenschaften).

Mit dem günstigsten Artikel soll weiterhin die Summe der Bestände aller Artikel
des Produkts ausgewiesen werden.

Haben mehere Artikel den gleichen Preis, soll der erste genommen werden.

Die aktuelle Artikelliste steht per HTTP-Schnittstelle zur Verfügung (Details s.u.).
Die transformierten Daten sollen per PUT in eine externe HTTP-Schnittstelle
hochgeladen werden.

### Format der Quelldatei
- Trennzeichen: Pipe (|)
- Zeilentrenner: LF (\n)
- Zeile 1: Header / Spaltennamen
- Spalten: id|produktId|name|beschreibung|preis|bestand (String|String|String|String|Float|Int)
- Hinweis: das Trennzeichen ist nicht in den Spalteninhalten vorhanden

### Format der Zieldatei
- Trennzeichen: Pipe (|)
- Zeilentrenner: LF (\n)
- Zeile 1: Header / Spaltennamen
- Spalten: produktId|name|beschreibung|preis|summeBestand (String|String|String|Float|Int)

### Preise
Preise sind mit einer oder zwei Nachkommastellen und "." als Trenner zwischen Euro und Cent ohne Währungssymbol angegeben.
    z.B.: 12.13 , 42.03 oder 90.0

### Schnittstellenbeschreibung Download
- HTTP GET /articles/:lines
  - lines legt die Anzahl der zurückgelieferten Artikel fest
- Response Body: die CSV-Datei

### Schnittstellenbeschreibung Upload
- HTTP PUT /products/:lines, Content-Type: text/csv
  - lines bezieht sich auf die Anzahl der zugrundeliegenden Artikel
- Response Code: 200 wenn alle Einträge erfolgreich verarbeitet werden konnten

