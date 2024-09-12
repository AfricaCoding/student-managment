package validator;

import java.lang.reflect.Field;

public interface AnnotationValidator {
    boolean validate(Field field, Object value) throws IllegalAccessException;
    String getErrorMessage();
}
