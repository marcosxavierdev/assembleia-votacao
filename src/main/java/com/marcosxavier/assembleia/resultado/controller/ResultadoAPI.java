package com.marcosxavier.assembleia.resultado.controller;

import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ResultadoAPI {

    @GetMapping(value = "/public/v1/resultado/{idPauta}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    ResultadoDto buscaResultadoPorPauta(@PathVariable String idPauta);
}
