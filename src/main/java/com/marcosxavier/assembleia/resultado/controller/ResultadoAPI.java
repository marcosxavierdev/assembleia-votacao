package com.marcosxavier.assembleia.resultado.controller;

import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface ResultadoAPI {

    @GetMapping(value = "/public/v1/resultado/{idPauta}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Resultado", summary = "Busca o resultado de um pauta pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultadoDto.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    ResultadoDto buscaResultadoPorPauta(@PathVariable String idPauta);

    @GetMapping(value = "/public/v1/resultados", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Resultado", summary = "Busca uma lista com todos resultados cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResultadoDto.class)))),
            })
    List<ResultadoDto> listarResultados();
}
