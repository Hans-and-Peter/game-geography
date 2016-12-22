package game.geography.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * Tests the whole service. This is a system test of the service (an integration test).
 */
// see http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {
    // TODO test: find a better name of test, based on service entry points? under test

    @LocalServerPort
    private int endpointPort;

//    changes port globally, but is ugly because of potential side effects
//    @Before
//    public void setPortForRestAssured() {
//        // see http://g00glen00b.be/spring-boot-rest-assured/
//        RestAssured.port = endpointPort;
//    }

    @Test
    public void should_own_land_when_occupying() {
        // see https://github.com/rest-assured/rest-assured/wiki/Usage#request-body
        Map<String, Object> landRequestJson = new HashMap<>();
        landRequestJson.put("occupier", "Peter der Große");

        given().
                contentType("application/json").
                body(landRequestJson).
                accept("application/json").
        when().
                port(endpointPort).
                put("/land/{landName}", "Stormland").
        then().
                statusCode(200).
                body("landName", is("Stormland"),
                        "owner", is("Peter der Große"));
    }

}
