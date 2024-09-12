package validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    private List<AnnotationValidator> validators = new ArrayList<>();

    public Validator() {
        validators.add(new NotBlankValidator());
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());
        validators.add(new ValidNameValidator());
    }

    public List<String> validate(Object obj) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);

            for (AnnotationValidator validator : validators) {
                if (!validator.validate(field, value)) {
                    errors.add(validator.getErrorMessage());
                }
            }
        }
        return errors;
    }
}
