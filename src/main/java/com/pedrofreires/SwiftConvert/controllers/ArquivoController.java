package com.pedrofreires.SwiftConvert.controllers;


import java.util.List;
import com.pedrofreires.SwiftConvert.domain.arquivo.ArquivoBodyDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.services.ArquivoServiceImpl;


@RestController
@RequestMapping("/api/arquivo")
public class ArquivoController {

    private final ArquivoServiceImpl arquivoService;

    @Autowired
    public ArquivoController(ArquivoServiceImpl arquivoService){
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

    @GetMapping("/convertedFile/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id") String id, @RequestBody ArquivoBodyDTO convertedData){
        return arquivoService.getFileConverted(id, convertedData.mimeType());
    }
}
