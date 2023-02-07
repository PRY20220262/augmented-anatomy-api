package com.pry20220262.augmentedanatomy.exception;

public enum Error {

    INVALID_DATA(1001, "Invalid Data"),
    USER_NOT_FOUND(1002, "User not found :("),
    DATA_NOT_FOUND(900, "Data not found it, try later")
    ;

    private final int codError;

    private final String message;

    Error(int codError, String message) {
        this.codError = codError;
        this.message = message;
    }

    public int getCodError() {
        return this.codError;
    }

    public String getMessage() {
        return this.message;
    }


}
