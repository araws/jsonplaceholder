import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonplaceholderPUTPATCHTest {

    @Test
    void jsonplaceholderUpdateUserPUTTest(){
        String jsonBody = """
                {
                    "name": "Andrzej Update PUT",
                    "username": "AndrzejPUT",
                    "email": "andrzejPUT@arit.pl",
                    "address": {
                        "street": "Kulas Light",
                        "suite": "Apt. 556",
                        "city": "Gwenborough",
                        "zipcode": "92998-3874",
                        "geo": {
                            "lat": "-37.3159",
                            "lng": "81.1496"
                        }
                    },
                    "phone": "1-770-736-8031 x56442",
                    "website": "hildegard.org",
                    "company": {
                        "name": "Romaguera-Crona",
                        "catchPhrase": "Multi-layered client-server neural-net",
                        "bs": "harness real-time e-markets"
                    }
                }""";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Andrzej Update PUT", json.get("name"));
        assertEquals("AndrzejPUT", json.get("username"));
        assertEquals("andrzejPUT@arit.pl", json.get("email"));
    }

    @Test
    void jsonplaceholderUpdateUserPATCHTest(){
        String jsonBody = """
                {
                    "email": "andrzejPATCH@arit.pl"
                }""";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .patch("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("andrzejPATCH@arit.pl", json.get("email"));
    }
}
