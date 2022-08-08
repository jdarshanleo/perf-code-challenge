package com.challenge.user.exceptions;

public class UserDoesNotExist extends RuntimeException {

    public UserDoesNotExist() {
        super("User does not exist");
    }

    public UserDoesNotExist(String errMsg) {
        super(errMsg);
    }
}
