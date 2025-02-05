package com.marcosxavier.assembleia.application.ports.out.persistenceMongodb;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.EleitorMongodbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleitorMongoDBRepository extends MongoRepository<EleitorMongodbEntity, String> {

    Optional<EleitorMongodbEntity> findById(String uuid);
    Optional<EleitorMongodbEntity> findByCpf(String cpf);
}
