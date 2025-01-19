package com.marcosxavier.assembleia.resultado.application.service;

import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import org.springframework.stereotype.Service;

@Service
public interface ResultadoService {

    ResultadoDto buscaResultadoPorPauta(String idPauta);
}
