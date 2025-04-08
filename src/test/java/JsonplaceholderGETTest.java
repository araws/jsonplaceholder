import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class JsonplaceholderGETTest {

    @Test
    void jsonplaceholderReadAllUsers(){
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users");
        System.out.println(response.asString());

        Assertions.assertEquals(200, response.statusCode());
    }
}
