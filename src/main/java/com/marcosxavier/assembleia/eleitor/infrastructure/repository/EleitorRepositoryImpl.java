package com.marcosxavier.assembleia.eleitor.infrastructure.repository;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import com.marcosxavier.assembleia.eleitor.infrastructure.persistence.EleitorMongoDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RequiredArgsConstructor
public class EleitorRepositoryImpl implements EleitorRepository{

    private final EleitorMongoDBRepository repository;

    @Override
    public Optional<Eleitor> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<EleitorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(EleitorResponseDTO::new) // ajuste conforme necessário
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Eleitor eleitor) {
        repository.delete(eleitor);
    }

    @Override
    public void save(Eleitor eleitor) {
        repository.save(eleitor);
    }
}
