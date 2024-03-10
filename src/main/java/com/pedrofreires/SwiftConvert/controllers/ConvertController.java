package com.pedrofreires.SwiftConvert.controllers;

import com.pedrofreires.SwiftConvert.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class ConvertController {

    private final ArquivoService arquivoService;

    @Autowired
    public ConvertController(ArquivoService arquivoService){
        this.arquivoService = arquivoService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> convertToPdf(@PathVariable("id") String id){
        return ResponseEntity.ok().body(this.arquivoService.convertPdfToJpg(id));
    }
}
