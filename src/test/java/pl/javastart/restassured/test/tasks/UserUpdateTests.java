package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserUpdateTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest(){

        User user = new User();

        user.setId(666);
        user.setUsername("l.walewski");
        user.setFirstName("Leon");
        user.setLastName("Walewski");
        user.setEmail("l.walewski@gmail.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        // Create user - POST
        given().contentType("application/json")
                .body(user)
                .when().post("user")
                .then().statusCode(200);

        //Update user = PUT
        user.setFirstName("Konstanty");
        user.setLastName("Siekierkowski");

        given().body(user)
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().put("user/{username}")
                .then().statusCode(200);

        //CHeck user - GET
        given().contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then().statusCode(200);

    }
}
