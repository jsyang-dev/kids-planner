package info.kidsplanner.user.application;

import info.kidsplanner.user.application.dto.UserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ParentIdValidator implements ConstraintValidator<ParentIdConstraint, UserRequest> {
    @Override
    public boolean isValid(UserRequest userRequest, ConstraintValidatorContext context) {
        if (userRequest.getUserType().isChild()) {
            return Objects.nonNull(userRequest.getParentId()) && userRequest.getParentId() > 0;
        }
        return true;
    }
}
