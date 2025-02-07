package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultadoUsecase {
    ResultadoDTO buscaResultadoPorPauta(String idPauta);
    List<ResultadoDTO> listaResultados();
}
