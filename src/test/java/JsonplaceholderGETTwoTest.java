import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

class JsonplaceholderGETTwoTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";

    @Test
    void jsonplaceholderReadAllUsers(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        Assertions.assertEquals(10, names.size());
    }

    @Test
    void jsonplaceholderReadOneUser(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));
    }

    // PATH VARIABLES

    @Test
    void jsonplaceholderReadOneUserWithPathVariable(){
        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(BASE_URL + "/" + USERS + "/{userId}")
                .then()
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Sincere@april.biz", json.get("email"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));
    }

    // QUERY PARAMS

    @Test
    void jsonplaceholderReadUsersWithQueryParams(){
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Leanne Graham", json.getList("name").get(0));
        Assertions.assertEquals("Bret", json.getList("username").get(0));
        Assertions.assertEquals("Sincere@april.biz", json.getList("email").get(0));
        Assertions.assertEquals("Kulas Light", json.getList("address.street").get(0));
    }

    @Test
    void jsonplaceholderReadAllUsersAndVerifyEmailAddress(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");
        List<String> plEmails = emails.stream()
                .filter(email -> email.toLowerCase().endsWith(".pl"))
                .toList();

        plEmails.forEach(email -> System.out.println("Found .pl email: " + email));

        if (plEmails.isEmpty()) {
            System.out.println("No email addresses ending in .pl were found.");
        }
    }
}
