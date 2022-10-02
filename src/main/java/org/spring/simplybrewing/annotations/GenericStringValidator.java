package org.spring.simplybrewing.annotations;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The type Generic string validator.
 *
 * @author Dario Iannaccone
 */
@Slf4j
public class GenericStringValidator implements ConstraintValidator<GenericStringValidation, String> {

    /**
     * Is valid boolean.
     *
     * @param s                          the s
     * @param constraintValidatorContext the constraint validator context
     * @return the boolean
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (s == null) return true;
            Pattern p = Pattern.compile("^[\\w\\-\\s\\!\\?\\:\\.\\*]*$");
            Matcher m = p.matcher(s);
            return m.matches();
        } catch (Exception e) {
            log.error("Error in GenericStringValidator");
        }
        return false;
    }
}
