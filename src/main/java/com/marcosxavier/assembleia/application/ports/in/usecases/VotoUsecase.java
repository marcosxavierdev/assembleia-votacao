package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoUsecase {
    VotoResponseDTO buscaPorId(String id);
    VotoResponseDTO criaVoto(VotoRequestDTO request);
    VotoResponseDTO atualizaVoto(VotoUpdateDTO update);
    List<VotoResponseDTO> buscaTodosVotos();
    void deletaVoto(String id);
    void zeraCollectionVoto();
}
