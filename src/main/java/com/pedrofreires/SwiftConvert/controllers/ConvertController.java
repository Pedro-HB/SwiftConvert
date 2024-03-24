package com.pedrofreires.SwiftConvert.controllers;

import com.pedrofreires.SwiftConvert.domain.arquivo.ArquivoBodyDTO;
import com.pedrofreires.SwiftConvert.services.ArquivoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/convert")
public class ConvertController {

    private final ArquivoServiceImpl arquivoService;

    @Autowired
    public ConvertController(ArquivoServiceImpl arquivoService){
        this.arquivoService = arquivoService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> convertFile(@PathVariable("id") String id, @RequestBody ArquivoBodyDTO arquivoBodyDTO){
        return ResponseEntity.ok().body(this.arquivoService.convertPdfToJpg(id, arquivoBodyDTO));
    }
}
