import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

class JsonplaceholderPostsGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @Test
    void jsonplaceholderReadAllUsers(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> titles = json.getList("title");
        Assertions.assertEquals(100, titles.size());
    }

    @Test
    void jsonplaceholderReadOneUser(){
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(1, (Integer) json.get("userId"));
        Assertions.assertEquals(1, (Integer) json.get("id"));
        Assertions.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                json.get("title"));
        Assertions.assertEquals("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
                json.get("body"));
    }

    // PATH VARIABLES

    @Test
    void jsonplaceholderReadOneUserWithPathVariable(){
        Response response = given()
                .pathParam("id", 1)
                .when()
                .get(BASE_URL + "/" + POSTS + "/{id}")
                .then()
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(1, (Integer) json.get("userId"));
        Assertions.assertEquals(1, (Integer) json.get("id"));
        Assertions.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                json.get("title"));
        Assertions.assertEquals("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
                json.get("body"));
    }

    // QUERY PARAMS

    @Test
    void jsonplaceholderReadUsersWithQueryParams(){
        Response response = given()
                .queryParam("title", "qui est esse")
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(1, json.getList("userId").get(0));
        Assertions.assertEquals(2, json.getList("id").get(0));
        Assertions.assertEquals("qui est esse", json.getList("title").get(0));
        Assertions.assertEquals("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla",
                json.getList("body").get(0));
    }
}
