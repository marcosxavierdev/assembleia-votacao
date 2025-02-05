package com.marcosxavier.assembleia.application.ports.out.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PautaRepository {

    Optional<PautaMongodbEntity> buscaPorId(String id);
    List<PautaResponseDTO> buscaLista();
    void deleta(PautaMongodbEntity pautaMongodbEntity);
    void salva(PautaMongodbEntity pautaMongodbEntity);
    void zeraCollectionPauta();
}
