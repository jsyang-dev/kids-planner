package info.kidsplanner.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Child extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Builder
    private Child(String email, String password, String name, String phone, LocalDate birthday) {
        super(email, password, name, phone, birthday, UserType.CHILD);
    }

    public void changeParent(Parent newParent) {
        if (Objects.isNull(newParent) || Objects.equals(this.parent, newParent)) {
            return;
        }
        
        final List<Child> childrenOfNewParent = newParent.getChildren();
        if (Objects.nonNull(this.parent)) {
            childrenOfNewParent.remove(this);
        }

        this.parent = newParent;
        childrenOfNewParent.add(this);
    }
}
