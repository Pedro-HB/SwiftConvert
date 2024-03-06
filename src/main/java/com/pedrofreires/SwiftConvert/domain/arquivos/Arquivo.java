package com.pedrofreires.SwiftConvert.domain.arquivos;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {

    @Id
    public Integer id;
    public String filename;
    public String pathDestino;
    public String pathOriginal;
    public String typeDestino;

    public Arquivo(String filename, String pathOriginal, String pathDestino, String ty){
        this.typeDestino = ty;
        this.filename = filename;
        this.pathDestino = pathDestino;
        this.pathOriginal = pathOriginal;
    }
}
