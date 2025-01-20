package com.marcosxavier.assembleia.pauta.persistence.repository;

import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.entities.Pauta;
import com.marcosxavier.assembleia.pauta.persistence.databaseinterface.PautaMongoDBRepository;
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
    public Optional<Pauta> buscaPorId(String id) {
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
    public void deleta(Pauta pauta) {
        log.info("PautaRepositoryImpl - deleta");
        repository.delete(pauta);
    }

    @Override
    public void salva(Pauta pauta) {
        log.info("PautaRepositoryImpl - salva");
        repository.save(pauta);
    }

    @Override
    public void zeraCollectionPauta() {
        log.info("PautaRepositoryImpl - zeraCollectionPauta");
        repository.deleteAll();
    }
}
