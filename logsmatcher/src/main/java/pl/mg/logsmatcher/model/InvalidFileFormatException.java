package pl.mg.logsmatcher.model;

public class InvalidFileFormatException extends Exception {

    public InvalidFileFormatException(String errorMessage) {
        super(errorMessage);
    }
}
