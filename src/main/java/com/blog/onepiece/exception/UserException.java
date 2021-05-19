package com.blog.onepiece.exception;

public class UserException extends RuntimeException {

    public static final String NOT_FOUND = "user was not found";

    public UserException(String message) {
        super(message);
    }
}
