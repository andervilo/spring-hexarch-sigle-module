package org.health.pacientes.infra.anotations.validations;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Required.Validator.class)
@Documented
public @interface Required {

    String label();

    String message() default ("Campo obrigatório!");

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Validator implements ConstraintValidator<Required, Object> {

        @Override
        public void initialize(Required constraintAnnotation) {
        }

        @Override
        public boolean isValid(Object object, ConstraintValidatorContext context) {
            if(object == null) return false;

            if (object instanceof String) {
                return StringUtils.isNotBlank((String) object);
            } else if (object instanceof BigDecimal) {
                return ((BigDecimal) object).compareTo(BigDecimal.ZERO) >= 0;
            } else if (object instanceof Number) {
                return ((Number) object).doubleValue() >= 0;
            } else if (object instanceof List<?> && ((List<?>) object).isEmpty()) {
                return false;
            }

            return true;
        }
    }
}
