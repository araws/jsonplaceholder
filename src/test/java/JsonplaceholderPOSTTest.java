import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonplaceholderPOSTTest {
    
    @Test
    void jsonplaceholderCreateNewUser(){
        String jsonBody = """
                {
                    "name": "Andrzej Testowy",
                    "username": "Andrzej",
                    "email": "andrzej@arit.pl",
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
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Andrzej Testowy", json.get("name"));
        assertEquals("Andrzej", json.get("username"));
        assertEquals("andrzej@arit.pl", json.get("email"));
    }
}
