package pl.javastart.restassured.test.parameters;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class QueryParamsTests {

    @Test
    public void givenExistingPetWithStatusSoldWhenGetPetWithSoldStatusThenPetWithStatusIsReturnedTest() {

        Category category = new Category();
        category.setId(666);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(777);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("sold");


        given().log().method().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().status().statusCode(200);

        Pet[] pets = given().log().method().log().uri()
                .queryParam("status", "sold")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().log().status().statusCode(200)
                .extract().as(Pet[].class);

        List<Pet> petsArray = Arrays.asList(pets);

        assertTrue(petsArray.size() > 0, "List \"pets\" is empty.");

        System.out.println("------------------------------------------------------");
        System.out.println("Rozmiar listy \"pets\" = " + petsArray.size());
        System.out.println("------------------------------------------------------");

    }

}
