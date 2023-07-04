package info.kidsplanner.user.application.dto;

import info.kidsplanner.user.Child;
import info.kidsplanner.user.User;
import info.kidsplanner.user.UserType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {
    private final Long id;
    private final String email;
    private final String name;
    private final String phone;
    private final LocalDate birthday;
    private final UserType userType;
    private final Long parentId;

    @Builder
    private UserResponse(Long id, String email, String name, String phone, LocalDate birthday, UserType userType, Long parentId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.userType = userType;
        this.parentId = parentId;
    }

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
