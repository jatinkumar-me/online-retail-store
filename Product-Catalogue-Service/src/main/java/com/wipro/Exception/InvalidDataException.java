package com.wipro.Exception;

import org.springframework.validation.BindingResult;

public class InvalidDataException extends RuntimeException {
    private final BindingResult bindingResult;

    public InvalidDataException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
