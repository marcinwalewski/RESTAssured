# RESTAssured

http://swaggerpetstore.przyklady.javastart.pl/



# Metoda log()
REQUEST:
given().log().all(). .. // Loguje wszystkie szczegóły żądania, w tym parametry, nagłówki i treść
given().log().body(). .. // Loguje tylko body żądania
given().log().cookies(). . .// Loguje tylko cookies
given().log().everything(). .. // Loguje wszystko, analogicznie jak (all)
given().log().headers(). .. // Loguje tylko nagłówki
given().log().method(). .. // Loguje tylko metodę
given().log().parameters(). .. // Loguje tylko parametry
given().log().params(). .. // Loguje tylko parametry żądania
given().log().uri(). .. // Loguję tylko ścieżkę endpointu

RESPONSE
then().log().body(). .. // Loguje body odpowiedzi
then().log().ifError(). .. // Loguje tylko w przypadku błędu
then().log().all(). .. // Loguje wszystko
then().log().status(). .. // Loguje tylko kod statusowy
then().log().statusLine(). .. // Loguje tylko linię statusową
then().log().headers(). .. // Loguje tylko nagłówki
then().log().cookies(). .. // Loguje tylko ciasteczka
then().log().everything(). .. // Loguje wszystko, analogicznie jak (all)
then().log().ifStatusCodeIsEqualTo(302). .. // Loguje tylko, gdy status kod jest równy X, w przykładzie jest to kod 302
then().log().ifStatusCodeMatches(matcher). .. // Analogicznie jak wyżej

//Dla żądania
given().log().ifValidationFails()...
//Dla odpowiedzi
...then().log().ifValidationFails()...
# --------------------------------------------------------------------------------------------------------


# SERIALIZACJA
To, co robi REST Assured, to pod spodem zamieni obiekt Javowy z danymi, czyli klasę Message na JSONa. Skąd framework wie, na co ma zamienić podany obiekt? Po wartości podanej w ramach metody contentType(), czyli "application/json". Analogicznie, gdybyśmy podali w ramach metody contentType() wartość "application/xml" to REST Assured zamieniłby podany obiekt na XMLa.

REST Assured do serializacji wykorzystuje najpopularniejsze frameworki do serializacji/deserializacji w Javie. Są nimi:

Dla formatu JSONa:
- Jackson 2 
- Jackson
- Gson
- Johnzon
- Eclipse Yasson

Dla formatu XML:
- JAXB

Należy zaznaczyć, że aby poprawnie korzystać z którejś z bibliotek do serializacji/deserializacji to musi ona znaleźć się w ramach naszego projektu. W ramach classpath.

Zamiana czystego JSONa na obiekt Javovy : http://www.jsonschema2pojo.org/
# --------------------------------------------------------------------------------------------------------

# DESERIALIZACJA
REST Assured w kwestii deserializacji działa w zasadzie tak samo, jak w wypadku serializacji, czyli:
* Wykorzystuje frameworki takie jak GSON, Jackson itd.
* Orientuje się jakiego formatu są dane na podstawie nagłówków contentType oraz Accept

# --------------------------------------------------------------------------------------------------------

# JsonPath
JsonPath jest to język/funkcjonalność/biblioteka, która pozwala nam poruszać się po odpowiedzi w formacie JSON w celu:
    * Wyciągnięcia z niej informacji, na przykład jakiejś wartości.
    * Wykonania asercji przy pomocy wbudowanych asercji we framework REST Assured.

JsonPath w swojej implementacji używa składni Groovy's GPath.

Ewaluator JsonPath :  https://jsonpath.com/
# --------------------------------------------------------------------------------------------------------
