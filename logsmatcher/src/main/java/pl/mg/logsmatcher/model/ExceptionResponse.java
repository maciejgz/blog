package pl.mg.logsmatcher.model;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String errorType;

    private String errorMessage;
}
