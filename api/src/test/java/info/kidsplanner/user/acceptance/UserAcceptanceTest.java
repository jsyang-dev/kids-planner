package info.kidsplanner.user.acceptance;

import info.kidsplanner.AcceptanceTest;
import info.kidsplanner.api.user.application.dto.UserRequest;
import info.kidsplanner.api.user.application.dto.UserResponse;
import info.kidsplanner.domain.user.UserType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static info.kidsplanner.user.acceptance.UserStep.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("사용자 인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {
    @DisplayName("부모를 생성한다.")
    @Test
    void createParent() {
        // given
        final UserRequest parentRequest = makeParentRequest();

        // when
        final ExtractableResponse<Response> response = 사용자_저장(parentRequest);

        // then
        사용자_저장됨(response);
    }

    @DisplayName("자녀를 생성한다.")
    @Test
    void createChild() {
        // given
        final UserResponse parentResponse = 사용자_저장되어_있음(makeParentRequest());
        final UserRequest childRequest = makeChildRequest(parentResponse.getId());

        // when
        final ExtractableResponse<Response> response = 사용자_저장(childRequest);

        // then
        사용자_저장됨(response);
    }

    private void 사용자_저장됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    private UserRequest makeParentRequest() {
        return UserRequest.builder()
                .userType(UserType.PARENT)
                .email("parent@email.com")
                .password("1234")
                .name("parent")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .build();
    }

    private static UserRequest makeChildRequest(Long parentId) {
        return UserRequest.builder()
                .userType(UserType.CHILD)
                .email("parent@email.com")
                .password("1234")
                .name("child")
                .phone("01012345678")
                .birthday(LocalDate.of(2009, 1, 1))
                .parentId(parentId)
                .build();
    }
}
