package com.pedrofreires.SwiftConvert.services.converter;

import java.util.Objects;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.repositories.ArquivosRepository;
import com.pedrofreires.SwiftConvert.domain.converter.ConverterService;


@Service
public class ConverterServiceImpl implements ConverterService {
    /*
    *   Classe responsável por fazer o intermedio entre a chamada de conversão e a conversão propriamente
    *   Depdende diretamente da existencia do serviço convert instalado na máquina que está utilizando.
    * */

    private static final String CommandPdfToJpg = "convert -verbose -density 150 -trim %s -quality 100 -flatten -sharpen 0x1.0 %s";

    private final ArquivosRepository arquivosRepository;

    @Autowired
    public ConverterServiceImpl(ArquivosRepository arquivosRepository){
        this.arquivosRepository = arquivosRepository;
    }

    public Boolean convertMimeTypeFile(Arquivo arquivo, String resultMimeType) {

        String filename = arquivo.filename;

        String extensionDestiny = MimeTypeSupport.getExtension(resultMimeType);
        String extensionOrigin = MimeTypeSupport.getExtension(arquivo.mimeType);

        assert extensionOrigin != null;
        assert extensionDestiny != null;

        String outputName = filename.replace(extensionOrigin, extensionDestiny);

        String pathOriginal = arquivo.location;
        String pathDestino = arquivo.getPathDestiny(outputName);

        Process processIO = CommandService.executeAcommand(this.getCommand(pathOriginal, pathDestino));

        boolean exitCode = !(processIO.exitValue() == 1);

        if(!exitCode){ return false; }

        arquivo.addConversion(new Arquivo(outputName, pathDestino, resultMimeType, new ArrayList<>()));

        this.arquivosRepository.save(arquivo);
        return true;
    }

    public String[] getCommand(String pathOriginal, String pathDestino){
        return String.format(CommandPdfToJpg, pathOriginal, pathDestino).split(" ");
    }
}
