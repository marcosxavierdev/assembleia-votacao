package com.marcosxavier.assembleia.adapters.outbound.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import com.marcosxavier.assembleia.application.ports.out.repositories.EleitorRepository;
import com.marcosxavier.assembleia.application.ports.out.persistenceMongodb.EleitorMongoDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RequiredArgsConstructor
public class EleitorRepositoryImpl implements EleitorRepository {

    private final EleitorMongoDBRepository repository;

    @Override
    public Optional<Eleitor> buscaPorId(String id) {
        log.info("EleitorRepositoryImpl - buscaPorId: {}", id);
        return repository.findById(id);
    }

    @Override
    public Optional<Eleitor> buscaPorCpf(String cpf) {
        log.info("EleitorRepositoryImpl - buscaPorCpf: {}", cpf);
        return repository.findByCpf(cpf);
    }

    @Override
    public void zeraCollectionEleitor() {
        log.info("EleitorRepositoryImpl - zeraCollectionEleitor");
        repository.deleteAll();
    }

    @Override
    public List<Eleitor> buscaLista() {
        log.info("EleitorRepositoryImpl - buscaLista");
        return repository.findAll().stream()
                .map(Eleitor::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleta(Eleitor eleitor) {
        log.info("EleitorRepositoryImpl - deleta");
        repository.delete(eleitor);
    }

    @Override
    public void salva(Eleitor eleitor) {
        log.info("EleitorRepositoryImpl - salva");
        repository.save(eleitor);
    }

    @Override
    public List<Eleitor> buscaListaPorCpf(String cpf) {
        log.info("EleitorRepositoryImpl - buscaListaPorCpf: {}", cpf);
        return repository.findAll().stream()
                .filter(eleitor -> eleitor.getCpf().equals(cpf))
                .map(Eleitor::new)
                .collect(Collectors.toList());
    }
}
