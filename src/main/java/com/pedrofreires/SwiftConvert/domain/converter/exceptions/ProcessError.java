package com.pedrofreires.SwiftConvert.domain.converter.exceptions;

public class ProcessError extends RuntimeException{

    public ProcessError(String s){
        super(s);
    }
    public ProcessError(String s, Throwable cause){
        super(s, cause);
    }
}
