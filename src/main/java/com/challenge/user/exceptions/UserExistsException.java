package com.challenge.user.exceptions;

public class UserExistsException extends RuntimeException {

    public UserExistsException() {
        super("This user already exists");
    }

    public UserExistsException(String errorMsg) {
        super(errorMsg);
    }
}
