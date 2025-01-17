package com.marcosxavier.assembleia.voto.infrastructure.persistence;

import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotoMongoDBRepository extends MongoRepository<Voto, Long> {
}
