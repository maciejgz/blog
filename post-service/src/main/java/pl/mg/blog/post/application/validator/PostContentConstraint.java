package pl.mg.blog.post.application.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PostContentValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostContentConstraint {

    String message() default "Invalid post content";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
