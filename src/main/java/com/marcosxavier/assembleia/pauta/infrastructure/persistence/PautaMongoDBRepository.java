package com.marcosxavier.assembleia.pauta.infrastructure.persistence;

import com.marcosxavier.assembleia.pauta.domain.entities.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaMongoDBRepository extends MongoRepository<Pauta, Long> {
}
