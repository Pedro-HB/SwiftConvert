package com.pedrofreires.SwiftConvert.repositories;

import com.pedrofreires.SwiftConvert.domain.arquivo.Arquivo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArquivosRepository extends MongoRepository<Arquivo, String> {
    // podemos especificar os métodos que devem ser implementados no repository
}
