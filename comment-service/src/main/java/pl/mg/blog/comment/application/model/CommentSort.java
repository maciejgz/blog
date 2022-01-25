package pl.mg.blog.comment.application.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommentSortValidator.class)
@Documented
public @interface CommentSort {

    String message() default "{CommentSort.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
