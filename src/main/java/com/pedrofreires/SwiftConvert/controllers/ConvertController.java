package com.pedrofreires.SwiftConvert.controllers;

import com.pedrofreires.SwiftConvert.services.ArquivoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class ConvertController {

    private final ArquivoServiceImpl arquivoService;

    @Autowired
    public ConvertController(ArquivoServiceImpl arquivoService){
        this.arquivoService = arquivoService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> convertToPdf(@PathVariable("id") String id){
        return ResponseEntity.ok().body(this.arquivoService.convertPdfToJpg(id));
    }
}
