package com.marcosxavier.assembleia.pauta.persistence.databaseinterface;

import com.marcosxavier.assembleia.pauta.domain.entities.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PautaMongoDBRepository extends MongoRepository<Pauta, String> {

    Optional<Pauta> findById(String uuid);
}
