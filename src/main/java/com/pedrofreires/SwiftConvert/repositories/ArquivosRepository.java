package com.pedrofreires.SwiftConvert.repositories;

import com.pedrofreires.SwiftConvert.domain.arquivos.Arquivo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArquivosRepository extends MongoRepository<Arquivo, Integer> {
    // podemos especificar os métodos que devem ser implementados no repository
}
