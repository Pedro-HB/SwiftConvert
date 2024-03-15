package com.pedrofreires.SwiftConvert.services.converter;

import com.pedrofreires.SwiftConvert.repositories.ArquivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import com.pedrofreires.SwiftConvert.domain.converter.ConverterService;

import java.util.ArrayList;


@Service
public class ConverterServiceImpl implements ConverterService {

    private final ArquivosRepository arquivosRepository;

    private static final String CommandPdfToJpg = "convert -verbose -density 150 -trim %s -quality 100 -flatten -sharpen 0x1.0 %s";

    @Autowired
    public ConverterServiceImpl(ArquivosRepository arquivosRepository){
        this.arquivosRepository = arquivosRepository;
    }

    public Boolean convertPdfToJpg(Arquivo arquivo) {

        String filename = arquivo.filename;
        String outputName = filename.replace(".pdf",".jpg");

        String pathOriginal = arquivo.location;
        String pathDestino = arquivo.getPathDestiny(outputName);

        Process processIO = CommandService.executeAcommand(this.getCommand(pathOriginal, pathDestino));

        boolean exitCode = !(processIO.exitValue() == 1);

        if(!exitCode){ return false; }

        arquivo.addConversion(new Arquivo(outputName, pathDestino, "image/jpg", new ArrayList<>()));

        this.arquivosRepository.save(arquivo);
        return true;
    }

    public String[] getCommand(String pathOriginal, String pathDestino){
        return String.format(CommandPdfToJpg, pathOriginal, pathDestino).split(" ");
    }
}
