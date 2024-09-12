package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String format() default "\\d{6,13}";
    String message() default "Numéro de téléphone invalide, il doit avoir entre 6 et 12 chiffres ";
}
