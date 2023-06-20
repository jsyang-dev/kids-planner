package info.kidsplanner.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

// TODO 부모, 자녀 클래스 분리
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    private User(String email, String password, String name, String phone, LocalDate birthday, UserType userType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.userType = userType;
    }
}
