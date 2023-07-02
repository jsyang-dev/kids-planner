package info.kidsplanner.user.acceptance;

import info.kidsplanner.AcceptanceStep;
import info.kidsplanner.api.user.application.dto.UserRequest;
import info.kidsplanner.api.user.application.dto.UserResponse;
import info.kidsplanner.util.AcceptanceUtil;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class UserStep extends AcceptanceStep {
    public static final String USER_URI = "/users";

    public static UserResponse 사용자_저장되어_있음(UserRequest userRequest) {
        return 사용자_저장(userRequest).as(UserResponse.class);
    }

    public static ExtractableResponse<Response> 사용자_저장(UserRequest userRequest) {
        return AcceptanceUtil.invokeHttpPost(givenSpec(), USER_URI, userRequest);
    }
}
