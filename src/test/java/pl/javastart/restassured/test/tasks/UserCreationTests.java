package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserCreationTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        User user = new User();
        user.setId(666);
        user.setUsername("mwalewski");
        user.setFirstName("Marcin");
        user.setLastName("Walewski");
        user.setEmail("walewski.marcin@gmail.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        given().body(user).contentType("application/json")
                .when().post("user")
                .then()
                .assertThat().body("code", equalTo(200))
                .assertThat().body("type", equalTo("unknown"))
                .assertThat().body("message", equalTo(user.getId().toString()))
                .assertThat().statusCode(200);

        given().contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then()
                .assertThat().body("id", equalTo(user.getId()))
                .assertThat().body("username", equalTo(user.getUsername()))
                .assertThat().body("firstName", equalTo(user.getFirstName()))
                .assertThat().body("lastName", equalTo(user.getLastName()))
                .assertThat().body("email", equalTo(user.getEmail()))
                .assertThat().body("password", equalTo(user.getPassword()))
                .assertThat().body("phone", equalTo(user.getPhone()))
                .assertThat().body("userStatus", equalTo(user.getUserStatus()))
                .assertThat().statusCode(200);

    }
}
