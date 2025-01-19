package com.marcosxavier.assembleia.eleitor.persistence.databaseinterface;

import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleitorMongoDBRepository extends MongoRepository<Eleitor, String> {

    Optional<Eleitor> findById(String uuid);
    Optional<Eleitor> findByCpf(String cpf);
}
