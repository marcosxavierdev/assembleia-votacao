package com.marcosxavier.assembleia.application.ports.out.persistenceMongodb;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.VotoMongodbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoMongoDBRepository extends MongoRepository<VotoMongodbEntity, String> {
    Optional<VotoMongodbEntity> findById(String uuid);
    List<VotoMongodbEntity> findByIdPautaAndIdEleitor(String idPauta, String idEleitor);
    Long countByIdPautaAndAprovacao(String idPauta, String aprovacao);
}
