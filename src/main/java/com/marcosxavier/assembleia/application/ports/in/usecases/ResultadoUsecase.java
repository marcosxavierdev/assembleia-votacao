package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultadoUsecase {
    ResultadoDto buscaResultadoPorPauta(String idPauta);
    List<ResultadoDto> listaResultados();
}
