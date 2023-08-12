package info.kidsplanner.user.acceptance;

import info.kidsplanner.AcceptanceStep;
import info.kidsplanner.user.application.dto.UserRequest;
import info.kidsplanner.user.application.dto.UserResponse;
import info.kidsplanner.user.ui.UserController;
import info.kidsplanner.util.AcceptanceUtil;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserStep extends AcceptanceStep {
    public static UserResponse 사용자_저장되어_있음(UserRequest userRequest) {
        return 사용자_저장(userRequest).as(UserResponse.class);
    }

    public static ExtractableResponse<Response> 사용자_저장(UserRequest userRequest) {
        return 사용자_저장(givenSpec(), userRequest);
    }

    public static ExtractableResponse<Response> 사용자_저장(RequestSpecification specification, UserRequest userRequest) {
        return AcceptanceUtil.invokeHttpPost(specification, UserController.USER_URI, userRequest);
    }
}
