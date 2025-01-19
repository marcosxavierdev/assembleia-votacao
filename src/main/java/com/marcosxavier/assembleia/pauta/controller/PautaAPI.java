package com.marcosxavier.assembleia.pauta.controller;

import com.marcosxavier.assembleia.pauta.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.dtos.PautaUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface PautaAPI {

    @GetMapping(value = "/public/v1/pauta/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Pauta", summary = "Busca uma pauta pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PautaResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
    })
    PautaResponseDTO buscaPorId(@PathVariable String id);

    @PostMapping(value = "/public/v1/pauta")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(tags = "Pauta", summary = "Cria uma pauta para a sessão de votação",
            responses = {
                    @ApiResponse(responseCode = "201", description= "CREATED", content = @Content(schema = @Schema(implementation = PautaResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description= "BAD REQUEST", content = {@Content()}),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    PautaResponseDTO criaPauta (@Valid @RequestBody PautaRequestDTO request);

    @PutMapping(value = "/public/v1/pauta")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Pauta", summary = "Atualiza uma pauta pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PautaResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description= "BAD REQUEST", content = {@Content()}),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    PautaResponseDTO atualizaPauta(@Valid @RequestBody PautaUpdateDTO update);

    @GetMapping(value = "/public/v1/pauta")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Pauta", summary = "Busca uma lista com todas pautas cadastradas",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PautaResponseDTO.class))),
            })
    List<PautaResponseDTO> buscaTodasPautas();

    @DeleteMapping(value = "/public/v1/pauta/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(tags = "Pauta", summary = "Deleta uma pauta com o id correspondente",
            responses = {
                    @ApiResponse(responseCode = "204", description= "NO CONTENT", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    void deletaPauta (@PathVariable String id);

    @DeleteMapping(value = "/private/v1/pauta")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(tags = "Pauta", summary = "Deleta todos os registros da collection - somente testes",
            responses = {
                    @ApiResponse(responseCode = "204", description= "NO CONTENT", content = @Content(mediaType = "application/json")),
            })
    void zeraCollectionPauta ();

    @PostMapping(value = "/public/v1/pauta/{id}/encerrar")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Pauta", summary = "encerra uma pauta pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PautaResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    PautaResponseDTO encerrarPauta (@PathVariable String id);
}
