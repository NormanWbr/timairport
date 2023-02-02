package be.technifutur.constraints;

import be.technifutur.constraints.validators.MoreThanXdaysValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoreThanXdaysValidator.class)
public @interface MoreThanXdays {

    public String message() default "Moins de 7 jours dans le passé";

    public Class<?>[] groups() default{};

    public Class<? extends Payload>[] payload() default {};

    int amount() default 7;
    ChronoUnit unit() default ChronoUnit.DAYS;

}
