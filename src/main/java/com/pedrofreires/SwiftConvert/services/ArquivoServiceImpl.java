package com.pedrofreires.SwiftConvert.services;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import com.pedrofreires.SwiftConvert.domain.arquivo.ArquivoBodyDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.repositories.ArquivosRepository;
import com.pedrofreires.SwiftConvert.services.converter.ConverterServiceImpl;
import com.pedrofreires.SwiftConvert.domain.arquivo.exceptions.ArquivoNotFound;


@Service
public class ArquivoServiceImpl {

    private final ConverterServiceImpl converter;
    private final ArquivosRepository arquivosRepository;
    private final StorageServiceImpl filesystemStorageService;


    @Autowired
    public ArquivoServiceImpl(ArquivosRepository arquivosRepository, StorageServiceImpl filesystemStorageService, ConverterServiceImpl converter) {
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

        String fileName = file.getOriginalFilename();
        assert fileName != null;
        
        if(Objects.equals(file.getContentType(), "image/jpeg")){
            fileName = fileName.replace(".jpg", ".jpeg");
        }

        String destinationPath = this.filesystemStorageService.store(file, fileName);
        Arquivo arquivo = new Arquivo(fileName, destinationPath, file.getContentType(), new ArrayList<>());

        this.arquivosRepository.save(arquivo);
        return arquivo;
    }

    public String convertPdfToJpg(String id, ArquivoBodyDTO arquivoBodyDTO){
        Arquivo arquivo = this.getOne(id);

        Boolean exited_process = converter.convertMimeTypeFile(arquivo, arquivoBodyDTO.mimeType());

        if(!exited_process){
            return "Não foi possível converter o arquivo !";
        }
        return "Conversão concluída !";
    }

    public ResponseEntity<Resource> getFileConverted(String id_arquivo, String type){
        Arquivo arquivo = this.getOne(id_arquivo);

        Object[] conversoes = arquivo.getConversoes().stream().filter((arq) -> Objects.equals(arq.mimeType, type)).toArray();

        if(conversoes.length < 1){
            throw new ArquivoNotFound("Não foi possivel encontrar o arquivo !");
        }
        Arquivo conversao_file = (Arquivo) conversoes[0];

        FileSystemResource resource = new FileSystemResource(conversao_file.location);
        MediaType mediaType = null;

        try{
            mediaType = MediaTypeFactory.getMediaType(resource).orElseThrow(FileNotFoundException::new);
        }catch (Exception exc){
            exc.getStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);

        ContentDisposition disposition = ContentDisposition.attachment()
                .filename(resource.getFilename()).build();

        headers.setContentDisposition(disposition);

        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}