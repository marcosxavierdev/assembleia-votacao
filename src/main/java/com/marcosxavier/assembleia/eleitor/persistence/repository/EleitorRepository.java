package com.marcosxavier.assembleia.eleitor.persistence.repository;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleitorRepository {

    Optional<Eleitor> buscaPorId(String id);
    List<EleitorResponseDTO> buscaLista();
    void deleta(Eleitor eleitor);
    void salva(Eleitor eleitor);
    List<Eleitor> buscaListaPorCpf(String cpf);
    Optional<Eleitor> buscaPorCpf(String cpf);
    void zeraCollectionEleitor();
}
