package com.marcosxavier.assembleia.eleitor.persistence.repository;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleitorRepository {

    Optional<Eleitor> findById(String id);
    List<EleitorResponseDTO> findAll();
    void delete(Eleitor eleitor);
    void save(Eleitor eleitor);

    List<Eleitor> findAllByCpf(String cpf);
}
