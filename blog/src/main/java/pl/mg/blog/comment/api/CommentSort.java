package pl.mg.blog.comment.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommentSortValidator.class)
@Documented
public @interface CommentSort {

    String message() default "{CommentSort.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
