package game.geography.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @SuppressWarnings("unused") // set by Spring
    @LocalServerPort
    private int endpointPort;

//    changes port globally, but is ugly because of potential side effects
//    @Before
//    public void setPortForRestAssured() {
//        // see http://g00glen00b.be/spring-boot-rest-assured/
//        RestAssured.port = endpointPort;
//    }

    @Test
    public void should_have_same_version() {
        given().
                accept("application/json").
        when().
                port(endpointPort).
                get("/version").
        then().
                statusCode(200).
                body("serviceVersion", is("1.0.0-17"));
    }

    @Test
    public void should_own_land_when_occupying() throws JsonProcessingException {
        // see https://github.com/rest-assured/rest-assured/wiki/Usage#request-body
        Map<String, Object> landRequestJson = new HashMap<>();
        landRequestJson.put("occupier", "Peter der Große");

        given().
                contentType("application/json").
                body(new ObjectMapper().writeValueAsString(landRequestJson)). 
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
