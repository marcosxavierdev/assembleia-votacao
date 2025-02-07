package com.marcosxavier.assembleia.application.ports.out.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleitorRepository {

    Optional<Eleitor> buscaPorId(String id);
    List<Eleitor> buscaLista();
    void deleta(Eleitor eleitor);
    void salva(Eleitor eleitor);
    List<Eleitor> buscaListaPorCpf(String cpf);
    Optional<Eleitor> buscaPorCpf(String cpf);
    void zeraCollectionEleitor();
}
