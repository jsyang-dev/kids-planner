package info.kidsplanner.user.acceptance;

import info.kidsplanner.AcceptanceTest;
import info.kidsplanner.api.user.application.dto.UserRequest;
import info.kidsplanner.domain.user.UserType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("사용자 인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {
    @DisplayName("부모 사용자를 생성한다.")
    @Test
    void createParentUser() {
        // given
        final UserRequest userRequest = UserRequest.builder()
                .userType(UserType.PARENT)
                .email("parent@email.com")
                .password("1234")
                .name("parent")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .build();

        // when
        final ExtractableResponse<Response> response = UserStep.사용자_저장_요청(userRequest);

        // then
        사용자_저장됨(response);
    }

    @DisplayName("자녀 사용자를 생성한다.")
    @Test
    void createChildUser() {
        // given
        final UserRequest userRequest = UserRequest.builder()
                .userType(UserType.CHILD)
                .email("parent@email.com")
                .password("1234")
                .name("child")
                .phone("01012345678")
                .birthday(LocalDate.of(2009, 1, 1))
                .build();

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
