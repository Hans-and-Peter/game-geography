package game.geography.systemtest;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * Tests the whole service. This is a system test of the deployed application.
 */
public class SystemTest {
    // TODO naming: find a better name of test, based on acceptance criterias? or requirements?

    private String baseUri = "http://127.0.0.1";
    private int endpointPort = 8080;

    // TODO @Before
    public void configureEnvironment() {
        baseUri = System.getProperty("game.environment.baseuri");
        endpointPort = Integer.getInteger("game.environment.port");
    }

    @Test
    public void should_own_land_when_occupying() {
        Map<String, Object> landRequestJson = new HashMap<>();
        landRequestJson.put("occupier", "Peter der Große");

        given().
                contentType("application/json").
                body(landRequestJson).
                accept("application/json").
        when().
                baseUri(baseUri).
                port(endpointPort).
                put("/land/{landName}", "Stormland").
        then().
                statusCode(200).
                body("landName", is("Stormland"),
                        "owner", is("Peter der Große"));
    }

}
