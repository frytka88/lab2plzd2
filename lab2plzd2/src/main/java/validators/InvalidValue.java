package validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InvalidValue implements ConstraintValidator<Invalid, String> {

    public Invalid invalid;

    public void initialize(Invalid invalid) {
        this.invalid = invalid;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            if (invalid.ignore() == false) {
                return Arrays.stream(invalid.myValue()).filter(x -> x.equals(value)).findFirst().isPresent() == false;
            } else {
                return Arrays.stream(invalid.myValue()).filter(x -> x.toLowerCase().equals(value.toLowerCase())).findFirst().isPresent() == false;
            }
        }
        return true;
    }
}
