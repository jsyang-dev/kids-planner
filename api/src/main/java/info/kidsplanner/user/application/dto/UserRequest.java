package info.kidsplanner.user.application.dto;

import info.kidsplanner.domain.user.User;
import info.kidsplanner.domain.user.UserType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
    @Length(min = 10, max = 11)
    private final String phone;

    @NotNull
    private final LocalDate birthday;

    @NotNull
    private final UserType userType;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .birthday(birthday)
                .build();
    }
}
