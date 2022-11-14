package br.com.teste.parking.core.validations.annotations;

import br.com.teste.parking.core.validations.validators.LicenseNumberBrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LicenseNumberBrValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LicenseNumberBr {
    String message() default "{msg.validacao.LicenseNumberBr.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
