package info.kidsplanner.user.application.dto;

import info.kidsplanner.user.Child;
import info.kidsplanner.user.User;
import info.kidsplanner.user.UserType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserResponse {
    private final Long id;
    private final String email;
    private final String name;
    private final String phone;
    private final LocalDate birthday;
    private final UserType userType;
    private final Long parentId;

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
