package com.marcosxavier.assembleia.eleitor.application.service;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EleitorService {
    EleitorResponseDTO buscaPorId(String id);
    EleitorResponseDTO criaEleitor(EleitorRequestDTO request);
    EleitorResponseDTO atualizaEleitor(EleitorUpdateDTO update);
    List<EleitorResponseDTO> buscaTodosEleitores();
    void deletaEleitor(String id);
}
