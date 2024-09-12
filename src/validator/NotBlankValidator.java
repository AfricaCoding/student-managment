package validator;

import annotation.NotBlank;

import java.lang.reflect.Field;

public class NotBlankValidator implements AnnotationValidator {

    private String errorMessage;

    @Override
    public boolean validate(Field field, Object value) throws IllegalAccessException {
        if (field.isAnnotationPresent(NotBlank.class)) {
            NotBlank notBlank = field.getAnnotation(NotBlank.class);
            if (value == null || value.toString().trim().isEmpty()) {
                errorMessage = notBlank.message();
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
