package com.pedrofreires.SwiftConvert.domain.arquivo.exceptions;

public class ArquivoNotFound extends RuntimeException{

    public ArquivoNotFound(){}
    public ArquivoNotFound(String message){
        super(message);
    }

    public ArquivoNotFound(String message, Throwable cause){
        super(message, cause);
    }
}
