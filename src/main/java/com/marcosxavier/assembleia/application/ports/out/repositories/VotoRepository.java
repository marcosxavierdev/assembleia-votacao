package com.marcosxavier.assembleia.application.ports.out.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Voto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository {

    Optional<Voto> buscaPorId(String id);
    List<Voto> buscaLista();
    void deleta(Voto voto);
    void salva(Voto voto);
    List<Voto> buscaTodasVotosPorIdPautaEIdEleitor(String idPauta, String idEleitor);
    void zeraCollectionVoto();
    Long contaVotosPorPautaAprovacao(String idPauta, String aprovacao);
}
