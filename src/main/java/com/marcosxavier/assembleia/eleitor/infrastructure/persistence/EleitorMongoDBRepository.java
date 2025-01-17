package com.marcosxavier.assembleia.eleitor.infrastructure.persistence;

import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EleitorMongoDBRepository extends MongoRepository<Eleitor, Long> {
}
