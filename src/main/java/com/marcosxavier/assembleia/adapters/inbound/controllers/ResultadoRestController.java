package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.application.ports.in.api.ResultadoAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.ResultadoUsecase;
import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ResultadoRestController implements ResultadoAPI {

    private final ResultadoUsecase service;

    @Override
    public ResultadoDto buscaResultadoPorPauta(String idPauta) {
        log.info("ResultadoRestController - buscando resultado: {}",idPauta);
        return service.buscaResultadoPorPauta(idPauta);
    }

    @Override
    public List<ResultadoDto> listarResultados() {
        log.info("ResultadoRestController - listando resultados");
        return service.listaResultados();
    }
}
