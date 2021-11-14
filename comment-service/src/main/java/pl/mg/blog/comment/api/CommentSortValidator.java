package pl.mg.blog.comment.api;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentSortValidator implements ConstraintValidator<CommentSort, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return CommentSortValues.contains(value);
    }
}
