package com.marcosxavier.assembleia.application.ports.out.persistenceMongodb;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleitorMongoDBRepository extends MongoRepository<Eleitor, String> {

    Optional<Eleitor> findById(String uuid);
    Optional<Eleitor> findByCpf(String cpf);
}
