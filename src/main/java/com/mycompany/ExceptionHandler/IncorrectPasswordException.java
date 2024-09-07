package com.mycompany.ExceptionHandler;

@SuppressWarnings("unused")
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("Invalid Password Please Try Again!");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}