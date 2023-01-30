package be.technifutur.java.timairport.model.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DemoForm {
    @NotNull
    @Pattern(regexp = "[A-Z]{1,3}")
    String nom;
    @PositiveOrZero
    Integer age;
}
