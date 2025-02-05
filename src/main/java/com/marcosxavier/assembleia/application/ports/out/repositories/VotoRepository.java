package com.marcosxavier.assembleia.application.ports.out.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.VotoMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository {

    Optional<VotoMongodbEntity> buscaPorId(String id);
    List<VotoResponseDTO> buscaLista();
    void deleta(VotoMongodbEntity votoMongodbEntity);
    void salva(VotoMongodbEntity votoMongodbEntity);
    List<VotoMongodbEntity> buscaTodasVotosPorIdPautaEIdEleitor(String idPauta, String idEleitor);
    void zeraCollectionVoto();
    Long contaVotosPorPautaAprovacao(String idPauta, String aprovacao);
}
