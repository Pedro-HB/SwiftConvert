package com.pedrofreires.SwiftConvert.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pedrofreires.SwiftConvert.domain.arquivos.Arquivo;
import com.pedrofreires.SwiftConvert.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/arquivo")
public class ArquivoController {

    private final ArquivoService arquivoService;

    @Autowired
    public ArquivoController(ArquivoService arquivoService){
        this.arquivoService = arquivoService;
    }

    @PostMapping
    public ResponseEntity<Arquivo> create(@RequestParam("file") MultipartFile file){
        // a diferença entre o requestBody e requestParam
        // é que o requestParam irá capturar apenas uma key do body.
        Arquivo arquivo = this.arquivoService.create(file);
        return ResponseEntity.ok().body(arquivo);
    }

    @GetMapping
    public ResponseEntity<List<Arquivo>> getAll(){
        List<Arquivo> arquivos = this.arquivoService.getAll();
        return ResponseEntity.ok().body(arquivos);
    }
}
