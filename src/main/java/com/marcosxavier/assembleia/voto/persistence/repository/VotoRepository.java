package com.marcosxavier.assembleia.voto.persistence.repository;

import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository {

    Optional<Voto> findById(String id);
    List<VotoResponseDTO> findAll();
    void delete(Voto voto);
    void save(Voto voto);
}
