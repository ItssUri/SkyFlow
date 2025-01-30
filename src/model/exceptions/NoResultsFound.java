package model.exceptions;

public class NoResultsFound extends Exception {
    public NoResultsFound(String errorMessage) {
        super(errorMessage);
    }
}
