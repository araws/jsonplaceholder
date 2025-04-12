import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class JsonplaceholderPostsDELETETest {

    @Test
    void jsonplaceholderDeletePost(){
        given()
                .when()
                .delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
