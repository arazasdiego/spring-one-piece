package com.blog.onepiece.exception;

public class PostException extends RuntimeException {

    public static final String NOT_FOUND = "post was not found.";

    public PostException(String message) {
        super(message);
    }
}
