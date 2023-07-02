package info.kidsplanner.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("CHILD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Child extends User {
    @ManyToOne
    private Parent parent;

    @Builder
    private Child(String email, String password, String name, String phone, LocalDate birthday) {
        super(email, password, name, phone, birthday, UserType.CHILD);
    }

    public void changeParent(Parent parent) {
        final List<Child> childrenOfParent = this.parent.getChildren();
        if (Objects.nonNull(this.parent)) {
            childrenOfParent.remove(this);
        }

        this.parent = parent;
        childrenOfParent.add(this);
    }
}
