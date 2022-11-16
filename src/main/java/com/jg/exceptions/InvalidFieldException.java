package com.jg.exceptions;

import org.springframework.validation.BindingResult;

public class InvalidFieldException extends RuntimeException {
    private final transient BindingResult bindingResult; //Transient para que no se serialice esta variable

    public InvalidFieldException(String message, BindingResult bindingResult){
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
