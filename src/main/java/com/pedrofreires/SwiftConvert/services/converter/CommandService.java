package com.pedrofreires.SwiftConvert.services.converter;

import java.io.IOException;
import com.pedrofreires.SwiftConvert.domain.converter.exceptions.ProcessError;


class CommandService {

    public static Process executeAcommand(String... command){

        Process process = null;
        ProcessBuilder processContext = new ProcessBuilder(command);

        try{
            process = processContext.start();
            // vai bloquear minha thread original e esperar por um retorno do meu processo
            process.waitFor();

        } catch( IOException err ){
            throw new ProcessError("Ocorreu um erro ao executar o processo !!", err.getCause());

        } catch ( InterruptedException e ) {
            throw new RuntimeException(e);
        }

        return process;
    }
}