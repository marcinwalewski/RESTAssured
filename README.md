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
