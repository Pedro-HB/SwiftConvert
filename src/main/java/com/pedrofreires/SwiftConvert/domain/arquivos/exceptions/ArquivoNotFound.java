package com.pedrofreires.SwiftConvert.domain.arquivos.exceptions;

import com.pedrofreires.SwiftConvert.repositories.ArquivosRepository;

public class ArquivoNotFound extends RuntimeException{

    public ArquivoNotFound(){}
    public ArquivoNotFound(String message){
        super(message);
    }

    public ArquivoNotFound(String message, Throwable cause){
        super(message, cause);
    }
}
