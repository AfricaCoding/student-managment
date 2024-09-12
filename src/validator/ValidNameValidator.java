package validator;

import annotation.ValidName;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class ValidNameValidator implements AnnotationValidator {

    private String errorMessage;

    @Override
    public boolean validate(Field field, Object value) throws IllegalAccessException {
        if (field.isAnnotationPresent(ValidName.class)) {
            ValidName validName = field.getAnnotation(ValidName.class);
            if (!Pattern.matches(validName.format(), value.toString())) {
                errorMessage = String.format(validName.message(), field.getName());
                return false;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
