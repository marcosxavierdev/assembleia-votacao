package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorRequestDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorUpdateDTO;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.EleitorMongodbEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleitorUsecase {
    EleitorResponseDTO buscaPorId(String id);
    EleitorResponseDTO criaEleitor(EleitorRequestDTO request);
    EleitorResponseDTO atualizaEleitor(EleitorUpdateDTO update);
    List<EleitorResponseDTO> buscaTodosEleitores();
    void deletaEleitor(String id);
    EleitorResponseDTO buscaPorCpf(String cpf);
    EleitorMongodbEntity buscaEleitorPorId(String id);
    void zeraCollectionEleitor();
}
