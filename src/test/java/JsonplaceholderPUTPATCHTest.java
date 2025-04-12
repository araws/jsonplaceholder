import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonplaceholderPUTPATCHTest {

    @Test
    void jsonplaceholderUpdateUserPUTTest(){
        JSONObject user = new JSONObject();
        user.put("name", "Andrzej Update PUT");
        user.put("username", "AndrzejPUT");
        user.put("email", "andrzejPUT@arit.pl");
        user.put("phone", "1-770-736-8031 x56442");
        user.put("website", "hildegard.org");

        JSONObject geo = new JSONObject();
        geo.put("lat", "-37.3159");
        geo.put("lng", "81.1496");

        JSONObject address = new JSONObject();
        address.put("street", "Kulas Light");
        address.put("street", "Kulas Light");
        address.put("city", "Gwenborough");
        address.put("zipcode", "92998-3874");
        address.put("geo", geo);

        user.put("address", address);

        JSONObject company = new JSONObject();
        company.put("name", "Romaguera-Crona");
        company.put("catchPhrase", "Multi-layered client-server neural-net");
        company.put("bs", "harness real-time e-markets");

        user.put("company", company);

        Response response = given()
                .contentType("application/json")
                .body(user.toString())
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

        JSONObject userDetails = new JSONObject();
        userDetails.put("email", "andrzejPATCH@arit.pl");

        Response response = given()
                .contentType("application/json")
                .body(userDetails.toString())
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
