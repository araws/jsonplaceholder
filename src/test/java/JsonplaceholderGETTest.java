import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

class JsonplaceholderGETTest {

    @Test
    void jsonplaceholderReadAllUsers(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        Assertions.assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();

        List<String> names = json.getList("name");

        Assertions.assertEquals(10, names.size());

    }

    @Test
    void jsonplaceholderReadOneUser(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1");

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
                .get("https://jsonplaceholder.typicode.com/users/{userId}");

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
                .get("https://jsonplaceholder.typicode.com/users/");

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Leanne Graham", json.getList("name").get(0));
        Assertions.assertEquals("Bret", json.getList("username").get(0));
        Assertions.assertEquals("Sincere@april.biz", json.getList("email").get(0));
        Assertions.assertEquals("Kulas Light", json.getList("address.street").get(0));
    }
}
