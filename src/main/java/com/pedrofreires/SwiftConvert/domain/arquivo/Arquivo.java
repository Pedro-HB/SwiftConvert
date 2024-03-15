package com.pedrofreires.SwiftConvert.domain.arquivo;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "arquivos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {

    @Id
    public String id;
    public String filename;
    public String mimeType;
    public String location;
    public List<Arquivo> conversoes;

    public Arquivo(String filename, String location, String ty, List<Arquivo> arquivos){
        this.mimeType = ty;
        this.filename = filename;
        this.location = location;
        this.conversoes = arquivos;
    }

    public String getPathDestiny(String outputName){
        return this.location.replace(this.filename, outputName);
    }

    public void addConversion(Arquivo arquivo){
        this.conversoes.add(arquivo);
    }
}
