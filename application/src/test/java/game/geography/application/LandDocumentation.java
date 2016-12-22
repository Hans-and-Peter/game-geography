package game.geography.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
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
    public void prepareDocumentation() {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(RestAssuredRestDocumentation.documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void document_owning_land_when_occupying() throws JsonProcessingException {
        Map<String, Object> landRequestJson = new HashMap<>();
        landRequestJson.put("occupier", "Peter der Große");

        given(this.documentationSpec).
                contentType("application/json").
                // TODO rest: Restassured.body(map) does not work on encodings != UTF8. Why?
                // body(landRequestJson). // does not work, why?
                // body(landRequestJson, ObjectMapperType.JACKSON_2). // does not work, why
                body(new ObjectMapper().writeValueAsString(landRequestJson)). // works
                accept("application/json").
            filter(document("land",
                    preprocessRequest(
                            modifyUris()
                                .scheme("http")
                                .host("api.example.com")
                                .removePort(),
                            prettyPrint(),
                            removeHeaders("Content-Length", "Host")),
                    preprocessResponse(
                            prettyPrint(),
                            removeHeaders("Content-Length", "Date")),
                    requestFields(
                            fieldWithPath("occupier").description("Name of the occupier of that land, e.g. 'King Ragnar'.")),
                    responseFields(
                            fieldWithPath("landName").description("Name of the land, e.g. 'Stormland'."),
                            fieldWithPath("owner").description("Name of the owner of that land, e.g. 'King Ragnar'.")))).
                            // TODO documentation: idea put field descriptions into annotations above the actual fields used for serialisation.
        when().
                port(endpointPort).
                put("/land/{landName}", "Stormland").
        then().
                statusCode(200);
    }

}
