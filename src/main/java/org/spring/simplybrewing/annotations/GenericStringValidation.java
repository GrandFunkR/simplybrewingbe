package org.spring.simplybrewing.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;


/**
 * The interface Generic string validation.
 *
 * @author Dario Iannaccone
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenericStringValidator.class)
public @interface GenericStringValidation {
    /**
     * Message string.
     *
     * @return the string
     */
    public String message() default "Invalid String";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    public Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    public Class<? extends Payload>[] payload() default {};
}
