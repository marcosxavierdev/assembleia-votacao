package com.marcosxavier.assembleia.application.ports.out.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaRepository {

    Optional<Pauta> buscaPorId(String id);
    List<Pauta> buscaLista();
    void deleta(Pauta pauta);
    void salva(Pauta pauta);
    void zeraCollectionPauta();
}
