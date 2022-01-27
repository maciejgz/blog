package pl.mg.blog.comment.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mg.blog.comment.application.model.ApiErrorResponse;
import pl.mg.blog.comment.core.model.exception.CommentAlreadyDislikedException;
import pl.mg.blog.comment.core.model.exception.CommentNotExistException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CommentApiExceptionHandler {

    @ExceptionHandler(value = {CommentAlreadyDislikedException.class, CommentAlreadyDislikedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiErrorResponse> handleLikeException(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage(), details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(value = {CommentNotExistException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ApiErrorResponse> handleCommentException(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getMessage(), details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

}
