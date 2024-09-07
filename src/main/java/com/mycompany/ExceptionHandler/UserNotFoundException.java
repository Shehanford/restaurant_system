package com.mycompany.ExceptionHandler;

@SuppressWarnings("unused")
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found! Please Check Email and Password!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}