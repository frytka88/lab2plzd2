package validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvalidValue.class)
public @interface Invalid {

    String message() default "Illegal model {model}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //własne parametry konfiguracyjne adnotacji
    String[] myValue() default {};

    boolean ignore() default false;
}
