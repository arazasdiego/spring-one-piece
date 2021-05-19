package com.blog.onepiece.exception;

public class CategoryException extends RuntimeException {

    public final static String NOT_FOUND = "category was not found";

    public CategoryException(String message) {
        super(message);
    }
}
