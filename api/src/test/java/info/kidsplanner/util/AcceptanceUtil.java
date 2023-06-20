package info.kidsplanner.util;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

public class AcceptanceUtil {
    private AcceptanceUtil() {
        throw new AssertionError();
    }

    public static ExtractableResponse<Response> invokeHttpPost(RequestSpecification givenSpec, String uri, Object data) {
        return givenSpec
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(data)
                .when().post(uri)
                .then().log().all()
                .extract();
    }
}
