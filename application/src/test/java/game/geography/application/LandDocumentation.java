package game.geography.application;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.operation.preprocess.RestAssuredPreprocessors.modifyUris;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LandDocumentation {
    // copied from spring-restdocs/samples/rest-assured
    // see https://github.com/spring-projects/spring-restdocs/

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private RequestSpecification documentationSpec;

    @LocalServerPort
    private int endpointPort;

    @Before
    public void setPortForRestAssured() {
        RestAssured.port = endpointPort;
    }

    @Before
    public void setUp() {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(RestAssuredRestDocumentation.documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void document_owning_land_when_occupying() {
        Map<String, Object> landRequestJson = new HashMap<>();
        landRequestJson.put("owner", "Peter der Große");

        given(this.documentationSpec).
                contentType("application/json").
                body(landRequestJson).
                accept("application/json").
            filter(document("land",
                    preprocessRequest(modifyUris()
                            .scheme("http")
                            .host("api.example.com")
                            .removePort()),
                    responseFields(
                            fieldWithPath("landName").description("Name of the land, e.g. 'Stormland'"),
                            fieldWithPath("owner").description("Name of the owner of that land, e.g. 'King Ragnar'")))).
        when().
                port(endpointPort).
                put("/land/{landName}", "Stormland").
        then().
                statusCode(200);
    } // TODO request field

}
