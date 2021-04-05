package pl.javastart.restassured.test.http.methods;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.*;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class BasicHttpMethodsTests {

    @BeforeClass
    public void setupConfiguration(){
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setName("Burek");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200);

        JsonPath jsonPathResponse = given()
                .pathParam("petId", pet.getId())
                .when().get("pet/{petId}")
                .then().statusCode(200)
                .extract().jsonPath();

        String petName = jsonPathResponse.getString("name");
        String actualCategoryName = jsonPathResponse.getString("category.name");
        String tagName = jsonPathResponse.getString("tags[0].name");

        assertEquals(petName, pet.getName(), "Pet name");
        assertEquals(actualCategoryName, pet.getCategory().getName(), "Pet category name");
        assertEquals(tagName, pet.getTags().get(0).getName(), "Pet Tag name");

    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {

        given().pathParam("petId", 1)
                .when().get("pet/{petId}")
                .then().statusCode(200);

    }

    @Test
    public void givenExistingPetWhenUpdatePetNameThenPetIsChangedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setName("Burek");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200);

        pet.setName("Reksio");

        given().body(pet).contentType("application/json")
                .when().put("pet")
                .then().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(445);
        pet.setCategory(category);
        pet.setName("Leon");
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200);

        given().contentType("application/json")
                .pathParam("petId", pet.getId())
                .when().delete("pet/{petId}")
                .then().statusCode(200);
    }

}
