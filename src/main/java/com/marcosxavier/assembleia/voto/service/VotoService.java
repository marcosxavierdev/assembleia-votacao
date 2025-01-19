package com.marcosxavier.assembleia.voto.service;

import com.marcosxavier.assembleia.voto.dtos.VotoRequestDTO;
import com.marcosxavier.assembleia.voto.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.dtos.VotoUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoService {
    VotoResponseDTO buscaPorId(String id);
    VotoResponseDTO criaVoto(VotoRequestDTO request);
    VotoResponseDTO atualizaVoto(VotoUpdateDTO update);
    List<VotoResponseDTO> buscaTodosVotos();
    void deletaVoto(String id);
    void zeraCollectionVoto();
}
