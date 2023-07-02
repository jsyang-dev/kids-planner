package info.kidsplanner.api.user.application.dto;

import info.kidsplanner.domain.user.Child;
import info.kidsplanner.domain.user.User;
import info.kidsplanner.domain.user.UserType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private LocalDate birthday;
    private UserType userType;
    private Long parentId;

    public static UserResponse of(User user) {
        if (user.isParent()) {
            return UserResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .birthday(user.getBirthday())
                    .userType(user.getUserType())
                    .build();
        }

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .userType(user.getUserType())
                .parentId(((Child) user).getParent().getId())
                .build();
    }
}
