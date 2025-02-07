package com.marcosxavier.assembleia.application.ports.out.persistenceMongodb;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PautaMongoDBRepository extends MongoRepository<Pauta, String> {

    Optional<Pauta> findById(String uuid);
}
