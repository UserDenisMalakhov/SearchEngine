package main.exceptions;

public class UnknownIndexingStatusException extends RuntimeException{
    public UnknownIndexingStatusException(String message) {
        super(message);
    }
}
