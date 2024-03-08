package com.pedrofreires.SwiftConvert.domain.arquivos;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arquivos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {

    @Id
    public String id;
    public String filename;
    public String pathDestino;
    public String typeDestino;

    public Arquivo(String filename, String pathDestino, String ty){
        this.typeDestino = ty;
        this.filename = filename;
        this.pathDestino = pathDestino;
    }
}
