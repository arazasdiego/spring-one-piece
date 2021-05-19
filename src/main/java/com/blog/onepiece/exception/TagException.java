package com.blog.onepiece.exception;

public class TagException extends RuntimeException {

    public static final String NOT_FOUND = "tag was not found.";

    public TagException(String message) {
        super(message);
    }
}
