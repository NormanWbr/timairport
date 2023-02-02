package be.technifutur.constraints.validators;


import be.technifutur.constraints.Not0;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Not0Validator implements ConstraintValidator<Not0, Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        return !value.equals(0L);
    }
}
