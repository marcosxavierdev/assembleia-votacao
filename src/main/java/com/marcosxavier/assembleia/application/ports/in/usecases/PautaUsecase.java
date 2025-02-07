package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaRequestDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaUsecase {
    Pauta buscaPorId(String id);
    Pauta criaPauta(PautaRequestDTO request);
    Pauta atualizaPauta(PautaUpdateDTO update);
    List<Pauta> buscaTodasPautas();
    void deletaPauta(String id);
    Pauta buscaPautaPorId(String id);
    void zeraCollectionPauta();
    Pauta encerraPauta(String id);
}
