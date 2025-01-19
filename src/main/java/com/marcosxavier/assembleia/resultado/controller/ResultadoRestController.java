package com.marcosxavier.assembleia.resultado.controller;

import com.marcosxavier.assembleia.resultado.service.ResultadoService;
import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ResultadoRestController implements ResultadoAPI {

    private final ResultadoService service;

    @Override
    public ResultadoDto buscaResultadoPorPauta(String idPauta) {
        return service.buscaResultadoPorPauta(idPauta);
    }
}
