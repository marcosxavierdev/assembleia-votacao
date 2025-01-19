package com.marcosxavier.assembleia.pauta.application.service;

import com.marcosxavier.assembleia.pauta.domain.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaUpdateDTO;
import com.marcosxavier.assembleia.pauta.domain.entities.Pauta;

import java.util.List;

public interface PautaService {

    PautaResponseDTO buscaPorId(String id);
    PautaResponseDTO criaPauta(PautaRequestDTO request);
    PautaResponseDTO atualizaPauta(PautaUpdateDTO update);
    List<PautaResponseDTO> buscaTodasPautas();
    void deletaPauta(String id);
    Pauta buscaPautaPorId(String id);
    void zeraCollectionPauta();
    PautaResponseDTO encerraPauta(String id);
}
