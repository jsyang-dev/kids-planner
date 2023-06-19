package info.kidsplanner.api.user.acceptance;

import info.kidsplanner.api.AcceptanceStep;
import info.kidsplanner.api.user.application.dto.UserRequest;
import info.kidsplanner.api.util.AcceptanceUtil;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class UserStep extends AcceptanceStep {
    public static final String USER_URI = "/users";

    public static ExtractableResponse<Response> 사용자_저장_요청(UserRequest userRequest) {
        return AcceptanceUtil.invokeHttpPost(givenSpec(), USER_URI, userRequest);
    }
}
