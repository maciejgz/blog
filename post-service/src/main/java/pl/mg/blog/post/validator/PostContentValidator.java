package pl.mg.blog.post.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostContentValidator implements ConstraintValidator<PostContentConstraint, String> {
    @Override
    public void initialize(PostContentConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //TODO mocked validation - shall me more complex
        return !StringUtils.contains(value, "post");
    }
}
