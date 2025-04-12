import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class JsonplaceholderPostsPOSTTest {
    
    @Test
    void jsonplaceholderCreateNewPost(){
        String jsonBody = """
                {
                "userId": 1,
                "title": "test title",
                "body": "test body"
                }""";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(1, (Integer) json.get("userId"));
        Assertions.assertEquals("test title", json.get("title"));
        Assertions.assertEquals("test body", json.get("body"));
    }
}
