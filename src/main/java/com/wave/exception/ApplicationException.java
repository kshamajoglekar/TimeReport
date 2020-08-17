package com.wave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends Exception {

    public ApplicationException(){
        super();
    }
    public ApplicationException(String msg){
        super(msg);
    }
}
