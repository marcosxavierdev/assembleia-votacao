package com.marcosxavier.assembleia.voto.persistence.repository;

import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository {

    Optional<Voto> buscaPorId(String id);
    List<VotoResponseDTO> buscaLista();
    void deleta(Voto voto);
    void salva(Voto voto);
    List<Voto> buscaTodasVotosPorIdPautaEIdEleitor(String idPauta, String idEleitor);
    void zeraCollectionVoto();
    Long contaVotosPorPautaAprovacao(String idPauta, String aprovacao);
}
