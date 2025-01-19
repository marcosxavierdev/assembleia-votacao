package com.marcosxavier.assembleia.eleitor.infrastructure.persistence;

import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EleitorMongoDBRepository extends MongoRepository<Eleitor, String> {

    Optional<Eleitor> findById(String uuid);
}
