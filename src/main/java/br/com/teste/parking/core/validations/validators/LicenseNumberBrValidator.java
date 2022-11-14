package br.com.teste.parking.core.validations.validators;

import br.com.teste.parking.core.validations.annotations.LicenseNumberBr;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class LicenseNumberBrValidator implements ConstraintValidator<LicenseNumberBr, String> {

    private Pattern licenseNumberBrPattern = Pattern.compile("[A-Z]{2,3}[0-9]{4}|[A-Z]{3,4}[0-9]{3}|[A-Z0-9]{7}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) return true;
        return licenseNumberBrPattern.matcher(value).matches();
    }
}
