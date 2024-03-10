package com.pedrofreires.SwiftConvert.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.repositories.ArquivosRepository;
import com.pedrofreires.SwiftConvert.services.converter.ConverterServiceImpl;
import com.pedrofreires.SwiftConvert.domain.arquivo.exceptions.ArquivoNotFound;


@Service
public class ArquivoService {

    private final ConverterServiceImpl converter;
    private final ArquivosRepository arquivosRepository;
    private final StorageServiceImpl filesystemStorageService;


    @Autowired
    public ArquivoService(ArquivosRepository arquivosRepository, StorageServiceImpl filesystemStorageService, ConverterServiceImpl converter) {
        this.converter = converter;
        this.arquivosRepository = arquivosRepository;
        this.filesystemStorageService = filesystemStorageService;
    }

    public List<Arquivo> getAll() {
        return this.arquivosRepository.findAll();
    }

    public Arquivo getOne(String id) {
        return this.arquivosRepository.findById(id).orElseThrow(ArquivoNotFound::new);
    }

    public Arquivo create(MultipartFile file) {

        String destinationPath = this.filesystemStorageService.store(file);
        Arquivo arquivo = new Arquivo(file.getOriginalFilename(), destinationPath, file.getContentType(), new ArrayList<>());

        this.arquivosRepository.save(arquivo);
        return arquivo;
    }

    public String convertPdfToJpg(String id){
        // validar se arquivo é um pdf mesmo !!!
        Arquivo arquivo = this.getOne(id);

        if(!arquivo.mimeType.equals("application/pdf")){
            return "O MimeType do arquivo informado não condiz com o método !";
        }

        Boolean exited_process = converter.convertPdfToJpg(arquivo);

        if(!exited_process){
            return "Não foi possível converter o arquivo !";
        }
        return "Conversão concluída !";
    }
}