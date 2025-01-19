package com.marcosxavier.assembleia.pauta.infrastructure.repository;

import com.marcosxavier.assembleia.pauta.domain.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.domain.entities.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaRepository {

    Optional<Pauta> findById(String id);
    List<PautaResponseDTO> findAll();
    void delete(Pauta pauta);
    void save(Pauta pauta);
}
