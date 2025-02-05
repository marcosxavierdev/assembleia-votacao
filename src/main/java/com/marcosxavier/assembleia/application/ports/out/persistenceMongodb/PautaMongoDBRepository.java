package com.marcosxavier.assembleia.application.ports.out.persistenceMongodb;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PautaMongoDBRepository extends MongoRepository<PautaMongodbEntity, String> {

    Optional<PautaMongodbEntity> findById(String uuid);
}
