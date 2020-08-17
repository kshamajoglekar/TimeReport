package com.wave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ValidationException extends Exception{
    public ValidationException(){
        super();
    }
    public ValidationException(String msg){
        super(msg);
    }
}
