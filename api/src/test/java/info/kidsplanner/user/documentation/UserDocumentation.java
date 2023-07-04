package info.kidsplanner.user.documentation;

import info.kidsplanner.Documentation;
import info.kidsplanner.user.UserType;
import info.kidsplanner.user.application.UserService;
import info.kidsplanner.user.application.dto.UserRequest;
import info.kidsplanner.user.application.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.time.LocalDate;

import static info.kidsplanner.user.acceptance.UserStep.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

@DisplayName("사용자 문서화")
public class UserDocumentation extends Documentation {
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("사용자를 생성한다.")
    void create() {
        // given
        final UserRequest userRequest = UserRequest.builder()
                .email("child@email.com")
                .password("1234")
                .name("child")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .userType(UserType.CHILD)
                .parentId(1L)
                .build();
        final UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .email("child@email.com")
                .name("child")
                .phone("01012345678")
                .birthday(LocalDate.of(1983, 1, 1))
                .userType(UserType.CHILD)
                .parentId(1L)
                .build();
        given(userService.create(any())).willReturn(userResponse);

        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("email").description("이메일(로그인 계정)"),
                fieldWithPath("password").description("비밀번호"),
                fieldWithPath("name").description("이름"),
                fieldWithPath("phone").description("전화번호"),
                fieldWithPath("birthday").description("생일"),
                fieldWithPath("userType").description("사용자 유형[부모:PARENT, 자녀:CHILD]"),
                fieldWithPath("parentId").description("부모 ID(사용자 유형이 자녀인 경우)"),
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("사용자 ID"),
                fieldWithPath("email").description("이메일(로그인 계정)"),
                fieldWithPath("name").description("이름"),
                fieldWithPath("phone").description("전화번호"),
                fieldWithPath("birthday").description("생일"),
                fieldWithPath("userType").description("사용자 유형[부모:PARENT, 자녀:CHILD]"),
                fieldWithPath("parentId").description("부모 ID(사용자 유형이 자녀인 경우)"),
        };

        // when
        사용자_저장(
                givenSpec(
                        "createUser",
                        requestFields(requestFieldDescriptors),
                        responseFields(responseFieldDescriptors)
                ),
                userRequest
        );
    }
}
