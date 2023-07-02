package info.kidsplanner.api.user.application;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ParentIdValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParentIdConstraint {
    String message() default "사용자 유형이 자녀이면 유효한 부모 ID가 입력되어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
