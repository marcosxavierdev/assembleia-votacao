package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.application.ports.in.api.ResultadoAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.ResultadoUsecase;
import com.marcosxavier.assembleia.domain.dto.resultado.CustomResultadoCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDTO;
import com.marcosxavier.assembleia.utils.assemblers.ResultadoAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ResultadoRestController implements ResultadoAPI {

    private final ResultadoUsecase service;
    private final ResultadoAssembler assembler;

    @Override
    public ResultadoDTO buscaResultadoPorPauta(String idPauta) {
        log.info("ResultadoRestController - buscando resultado: {}",idPauta);
        var resultado = service.buscaResultadoPorPauta(idPauta);
        return assembler.toModel(resultado);
    }

    @Override
    public CustomResultadoCollectionDTO<ResultadoDTO> listarResultados() {
        log.info("ResultadoRestController - listando resultados");
        List<ResultadoDTO> resultados = service.listaResultados();
        return assembler.toCollectionModel(resultados);
    }
}
