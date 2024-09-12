package validator;

import annotation.Email;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class EmailValidator implements AnnotationValidator {

    private String errorMessage;

    @Override
    public boolean validate(Field field, Object value) throws IllegalAccessException {
        if (field.isAnnotationPresent(Email.class)) {
            Email email = field.getAnnotation(Email.class);
            String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            if (!Pattern.matches(regex, value.toString())) {
                errorMessage = email.message();
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
