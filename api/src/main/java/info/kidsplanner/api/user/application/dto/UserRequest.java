package info.kidsplanner.api.user.application.dto;

import info.kidsplanner.domain.user.Child;
import info.kidsplanner.domain.user.Parent;
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

    private final Long parentId;

    public Parent toParent() {
        if (userType.isParent()) {
            return Parent.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .birthday(birthday)
                    .build();
        }
        return null;
    }

    public Child toChild(Parent parent) {
        if (userType.isChild()) {
            final Child child = Child.builder()
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .birthday(birthday)
                    .build();
            child.changeParent(parent);
            return child;
        }
        return null;
    }
}
