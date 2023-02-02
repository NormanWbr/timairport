package be.technifutur.constraints.validators;

import be.technifutur.constraints.MoreThanXdays;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MoreThanXdaysValidator implements ConstraintValidator<MoreThanXdays, LocalDate> {

    private MoreThanXdays constraint;

    @Override
    public void initialize(MoreThanXdays constraintAnnotation) {
        constraint = constraintAnnotation;
    }

    public boolean isValid(LocalDate date, ConstraintValidatorContext context){
        ChronoUnit unit = constraint.unit();
        int amount = constraint.amount();
        if(!date.isBefore(LocalDate.now().minus(amount, unit)))
            context.buildConstraintViolationWithTemplate(String.format("Should be minimum %s %s in the past", amount, unit))
                    .addConstraintViolation();
        return true;
    }
}

