package info.kidsplanner.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("CHILD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Child extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Builder
    private Child(String email, String password, String name, String phone, LocalDate birthday) {
        super(email, password, name, phone, birthday, UserType.CHILD);
    }

    public void changeParent(Parent parent) {
        final List<Child> childrenOfNewParent = parent.getChildren();
        if (Objects.nonNull(this.parent)) {
            childrenOfNewParent.remove(this);
        }

        this.parent = parent;
        childrenOfNewParent.add(this);
    }
}
