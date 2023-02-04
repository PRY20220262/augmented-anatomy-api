package com.pry20220262.augmentedanatomy.exception;

import org.springframework.validation.BindingResult;

public class InvalidDataException extends RuntimeException{
    private final transient BindingResult result;

    private final Error error;

    public  InvalidDataException(BindingResult result, Error error) {
        super();
        this.result  = result;
        this.error = error;
    }

    public BindingResult getResult() {
        return result;
    }

    public Error getError() {
        return error;
    }
}
