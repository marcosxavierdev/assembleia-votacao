package com.marcosxavier.assembleia.adapters.outbound.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.VotoMongodbEntity;
import com.marcosxavier.assembleia.application.ports.out.repositories.VotoRepository;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.application.ports.out.persistenceMongodb.VotoMongoDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RequiredArgsConstructor
public class VotoRepositoryImpl implements VotoRepository {

    private final VotoMongoDBRepository repository;

    @Override
    public Optional<VotoMongodbEntity> buscaPorId(String id) {
        log.info("VotoRepositoryImpl - buscaPorId: {}", id);
        return repository.findById(id);
    }

    @Override
    public List<VotoResponseDTO> buscaLista() {
        log.info("VotoRepositoryImpl - buscaLista");
        return repository.findAll().stream()
                .map(VotoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleta(VotoMongodbEntity votoMongodbEntity) {
        log.info("VotoRepositoryImpl - deleta");
        repository.delete(votoMongodbEntity);
    }

    @Override
    public void salva(VotoMongodbEntity votoMongodbEntity) {
        log.info("VotoRepositoryImpl - salva");
        repository.save(votoMongodbEntity);
    }

    @Override
    public List<VotoMongodbEntity> buscaTodasVotosPorIdPautaEIdEleitor(String idPauta, String idEleitor) {
        log.info("VotoRepositoryImpl - buscaTodasVotosPorIdPautaEIdEleitor: {} e {}", idPauta, idEleitor);
        return repository.findByIdPautaAndIdEleitor(idPauta,idEleitor);
    }

    @Override
    public void zeraCollectionVoto() {
        log.info("VotoRepositoryImpl - zeraCollectionEleitor");
        repository.deleteAll();
    }

    @Override
    public Long contaVotosPorPautaAprovacao(String idPauta, String aprovacao) {
        log.info("VotoRepositoryImpl - contaVotosPorPautaAprovacao: {} e {}", idPauta, aprovacao);
        return repository.countByIdPautaAndAprovacao(idPauta, aprovacao);
    }
}
