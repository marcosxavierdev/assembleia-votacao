package com.marcosxavier.assembleia.voto.infrastructure.persistence;

import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoMongoDBRepository extends MongoRepository<Voto, String> {
    Optional<Voto> findById(String uuid);
}
