package info.kidsplanner.user.acceptance;

import info.kidsplanner.AcceptanceTest;
import info.kidsplanner.user.application.dto.UserRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

public class UserAcceptanceTest extends AcceptanceTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("사용자를 생성한다.")
    @Test
    void createUser() {
        // given
        final UserRequest userRequest = new UserRequest();

        // when
        final ExtractableResponse<Response> response = UserStep.사용자_저장_요청(userRequest);

        // then
        사용자_저장됨(response);
    }

    private void 사용자_저장됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }
}
