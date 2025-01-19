package com.marcosxavier.assembleia.resultado.service;

import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultadoService {

    ResultadoDto buscaResultadoPorPauta(String idPauta);
    List<ResultadoDto> listaResultados();
}
