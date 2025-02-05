package com.marcosxavier.assembleia.application.ports.out.repositories;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.EleitorMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EleitorRepository {

    Optional<EleitorMongodbEntity> buscaPorId(String id);
    List<EleitorResponseDTO> buscaLista();
    void deleta(EleitorMongodbEntity eleitorMongodbEntity);
    void salva(EleitorMongodbEntity eleitorMongodbEntity);
    List<EleitorMongodbEntity> buscaListaPorCpf(String cpf);
    Optional<EleitorMongodbEntity> buscaPorCpf(String cpf);
    void zeraCollectionEleitor();
}
