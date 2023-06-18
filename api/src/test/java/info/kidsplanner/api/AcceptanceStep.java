package info.kidsplanner.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class AcceptanceStep {
    public static RequestSpecification givenSpec() {
        return RestAssured
                .given().log().all();
    }
}
