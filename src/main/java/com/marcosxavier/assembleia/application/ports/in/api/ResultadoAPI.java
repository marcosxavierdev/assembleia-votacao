package com.marcosxavier.assembleia.application.ports.in.api;

import com.marcosxavier.assembleia.domain.dto.resultado.CustomResultadoCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ResultadoAPI {

    @GetMapping(value = "/public/v1/resultado/{idPauta}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Resultado", summary = "Busca o resultado de um pauta pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultadoDTO.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    ResultadoDTO buscaResultadoPorPauta(@PathVariable String idPauta);

    @GetMapping(value = "/public/v1/resultados", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Resultado", summary = "Busca uma lista com todos resultados cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResultadoDTO.class)))),
            })
    CustomResultadoCollectionDTO<ResultadoDTO> listarResultados();
}
