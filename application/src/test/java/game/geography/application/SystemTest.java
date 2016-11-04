package game.geography.application;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTest {

    @LocalServerPort
    private int endpointPort;

    @Before
    public void setPortForRestAssured() {
        RestAssured.port = endpointPort;
    }

    @Test
    public void should_own_land_when_occupying() {
        Map<String, Object> landRequestJson = new HashMap<>();
        landRequestJson.put("owner", "Peter der Große");

        given().
                contentType("application/json").
                body(landRequestJson).
        when().
                put("/land/{landname}", "Stormland").
        then().
                statusCode(200).
                body("landname", is("Stormland"),
                        "owner", is("Peter der Große"));

    }

}
