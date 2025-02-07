package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Voto;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoUsecase {
    Voto buscaPorId(String id);
    Voto criaVoto(VotoRequestDTO request);
    Voto atualizaVoto(VotoUpdateDTO update);
    List<Voto> buscaTodosVotos();
    void deletaVoto(String id);
    void zeraCollectionVoto();
}
