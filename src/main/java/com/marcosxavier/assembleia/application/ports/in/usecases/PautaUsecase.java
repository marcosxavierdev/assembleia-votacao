package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaRequestDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaUsecase {
    PautaResponseDTO buscaPorId(String id);
    PautaResponseDTO criaPauta(PautaRequestDTO request);
    PautaResponseDTO atualizaPauta(PautaUpdateDTO update);
    List<PautaResponseDTO> buscaTodasPautas();
    void deletaPauta(String id);
    PautaMongodbEntity buscaPautaPorId(String id);
    void zeraCollectionPauta();
    PautaResponseDTO encerraPauta(String id);
}
