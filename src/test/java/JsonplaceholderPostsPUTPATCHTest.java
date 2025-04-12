import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonplaceholderPostsPUTPATCHTest {

    private static Faker faker;
    private String fakeUserId;
    private String fakeTitle;
    private String fakeBody;

    @BeforeAll
    static void beforeAll(){
        faker = new Faker();
    }

    @BeforeEach
    void beforeEach(){
        fakeUserId = faker.idNumber().ssnValid();
        fakeTitle = faker.book().title();
        fakeBody = String.valueOf(faker.lorem());
    }

    @Test
    void jsonplaceholderUpdatePostPUTTest(){

        JSONObject post = new JSONObject();
        post.put("userId", fakeUserId);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeUserId, json.get("userId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));
    }

    @Test
    void jsonplaceholderUpdatePostPATCHTest(){

        JSONObject postDetails = new JSONObject();
        postDetails.put("title", fakeTitle);

        Response response = given()
                .contentType("application/json")
                .body(postDetails.toString())
                .when()
                .patch("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
    }
}
