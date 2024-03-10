package com.pedrofreires.SwiftConvert.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import com.pedrofreires.SwiftConvert.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;


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
        return ResponseEntity.ok().body(this.arquivoService.create(file));
    }

    @GetMapping
    public ResponseEntity<List<Arquivo>> getAll(){
        return ResponseEntity.ok().body(this.arquivoService.getAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Arquivo> getView(@PathVariable("id") String id){
        return ResponseEntity.ok().body(this.arquivoService.getOne(id));
    }
}
