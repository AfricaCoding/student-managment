package validator;

import annotation.Phone;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class PhoneValidator implements AnnotationValidator {

    private String errorMessage;

    @Override
    public boolean validate(Field field, Object value) throws IllegalAccessException {
        if (field.isAnnotationPresent(Phone.class)) {
            Phone phone = field.getAnnotation(Phone.class);
            if (!Pattern.matches(phone.format(), value.toString())) {
                errorMessage = phone.message();
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
