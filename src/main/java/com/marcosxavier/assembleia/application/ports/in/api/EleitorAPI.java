package com.marcosxavier.assembleia.application.ports.in.api;

import com.marcosxavier.assembleia.domain.dto.eleitor.CustomEleitorCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorRequestDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface EleitorAPI {

    @GetMapping(value = "/public/v1/eleitor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Eleitor", summary = "Busca uma eleitor pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EleitorResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    EleitorResponseDTO buscaPorId(@PathVariable String id);

    @GetMapping(value = "/public/v1/eleitor/byCPF/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Eleitor", summary = "Busca um eleitor pelo cpf correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EleitorResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    EleitorResponseDTO buscaPorCpf(@PathVariable String cpf);

    @PostMapping(value = "/public/v1/eleitor")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(tags = "Eleitor", summary = "Cadastra um eleitor para a sessões de votação",
            responses = {
                    @ApiResponse(responseCode = "201", description= "CREATED", content = @Content(schema = @Schema(implementation = EleitorResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description= "BAD REQUEST", content = {@Content()}),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
    })
    EleitorResponseDTO criaEleitor (@Valid @RequestBody EleitorRequestDTO request);

    @PutMapping(value = "/public/v1/eleitor")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Eleitor", summary = "Atualiza o registro de um eleitor pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EleitorResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description= "BAD REQUEST", content = {@Content()}),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    EleitorResponseDTO atualizaEleitor(@Valid @RequestBody EleitorUpdateDTO update);

    @GetMapping(value = "/public/v1/eleitor")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Eleitor", summary = "Busca uma lista com todos eleiores cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EleitorResponseDTO.class))),
            })
    CustomEleitorCollectionDTO<EleitorResponseDTO> buscaTodosEleitores();

    @DeleteMapping(value = "/public/v1/eleitor/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(tags = "Eleitor", summary = "Deleta o cadastro de um eleitor com o id correspondente",
            responses = {
                    @ApiResponse(responseCode = "204", description= "NO CONTENT", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    ResponseEntity<Void> deletaEleitor (@PathVariable String id);

    @DeleteMapping(value = "/private/v1/eleitor")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(tags = "Eleitor", summary = "Deleta todos os registros da collection - somente testes",
            responses = {
                    @ApiResponse(responseCode = "204", description= "NO CONTENT", content = @Content(mediaType = "application/json")),
            })
    ResponseEntity<Void> zeraCollectionEleitor ();
}
