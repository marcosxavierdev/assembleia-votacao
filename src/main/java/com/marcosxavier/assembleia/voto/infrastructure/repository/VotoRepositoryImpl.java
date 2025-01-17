package com.marcosxavier.assembleia.voto.infrastructure.repository;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import com.marcosxavier.assembleia.eleitor.infrastructure.persistence.EleitorMongoDBRepository;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import com.marcosxavier.assembleia.voto.infrastructure.persistence.VotoMongoDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Log4j2
@RequiredArgsConstructor
public class VotoRepositoryImpl implements VotoRepository{

    private final VotoMongoDBRepository repository;

    @Override
    public Optional<Voto> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<VotoResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(VotoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Voto voto) {
        repository.delete(voto);
    }

    @Override
    public void save(Voto voto) {
        repository.save(voto);
    }
}
