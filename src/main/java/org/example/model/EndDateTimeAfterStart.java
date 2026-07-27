package org.example.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.exception.EndDateTimeAfterStartValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = EndDateTimeAfterStartValidator.class)
public @interface EndDateTimeAfterStart {
    String message() default "Дата и время конца, должны быть после начала";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
