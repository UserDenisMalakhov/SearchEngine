package main.exceptions;

public class UnspecifiedInternalError extends RuntimeException{
    public UnspecifiedInternalError(String message) {
        super(message);
    }
}