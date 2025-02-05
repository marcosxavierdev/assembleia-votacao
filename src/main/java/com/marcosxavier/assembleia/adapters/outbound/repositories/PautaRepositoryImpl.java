package com.marcosxavier.assembleia.adapters.outbound.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
import com.marcosxavier.assembleia.application.ports.out.repositories.PautaRepository;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.application.ports.out.persistenceMongodb.PautaMongoDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PautaRepositoryImpl implements PautaRepository {

    private final PautaMongoDBRepository repository;

    @Override
    public Optional<PautaMongodbEntity> buscaPorId(String id) {
        log.info("PautaRepositoryImpl - buscaPorId: {}", id);
        return repository.findById(id);
    }

    @Override
    public List<PautaResponseDTO> buscaLista() {
        log.info("PautaRepositoryImpl - buscaLista");
        return repository.findAll().stream()
                .map(PautaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleta(PautaMongodbEntity pautaMongodbEntity) {
        log.info("PautaRepositoryImpl - deleta");
        repository.delete(pautaMongodbEntity);
    }

    @Override
    public void salva(PautaMongodbEntity pautaMongodbEntity) {
        log.info("PautaRepositoryImpl - salva");
        repository.save(pautaMongodbEntity);
    }

    @Override
    public void zeraCollectionPauta() {
        log.info("PautaRepositoryImpl - zeraCollectionPauta");
        repository.deleteAll();
    }
}
