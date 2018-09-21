## Akka Stack Sample

This is a sample project to demonstrate the use of the akka stack (akka-http, akka-streams)
it consumes a CSV source of articles in different variations  of a product.

Unavailable articles are not processed. The stock gets summed up and the article with the 
lowest price is used to label the prouct.




### Format of source
- Delimiter: Pipe (|)
- Row 1: Header / Spaltennamen
- Columns: id|produktId|name|beschreibung|preis|bestand (String|String|String|String|Float|Int)

### Format of target
- Trennzeichen: Pipe (|)
- Row 1: Header / Spaltennamen
- Columns: produktId|name|beschreibung|preis|summeBestand (String|String|String|Float|Int)

