package com.pedrofreires.SwiftConvert.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.pedrofreires.SwiftConvert.domain.arquivos.Arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.repositories.ArquivosRepository;
import com.pedrofreires.SwiftConvert.domain.arquivos.exceptions.ArquivoNotFound;


@Service
public class ArquivoService {

    public FilesystemStorageService storageService;
    public ArquivosRepository arquivosRepository;

    @Autowired
    public ArquivoService(ArquivosRepository arquivosRepository, FilesystemStorageService storageService) {
        this.storageService = storageService;
        this.arquivosRepository = arquivosRepository;
    }

    public List<Arquivo> getAll() {
        return this.arquivosRepository.findAll();
    }

    public ResponseEntity<Arquivo> getOne(Integer id) {
        Arquivo arquivo = this.arquivosRepository.findById(id).orElseThrow(ArquivoNotFound::new); // create the arquivo not found
        return ResponseEntity.ok().body(arquivo);
    }

    public Arquivo create(MultipartFile file) {

        String destinationPath = this.storageService.store(file);
        Arquivo arquivo = new Arquivo(file.getOriginalFilename(), destinationPath, file.getContentType());

        this.arquivosRepository.save(arquivo);
        return arquivo;
    }
}