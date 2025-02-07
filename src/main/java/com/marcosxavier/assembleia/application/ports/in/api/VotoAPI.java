package com.marcosxavier.assembleia.application.ports.in.api;

import com.marcosxavier.assembleia.domain.dto.voto.CustomVotoCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface VotoAPI {

    @GetMapping(value = "/public/v1/voto/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Voto", summary = "Busca um voto pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VotoResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    VotoResponseDTO buscaPorId(@PathVariable String id);

    @PostMapping(value = "/public/v1/voto")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(tags = "Voto", summary = "Cadastra um voto para a sessões de votação",
            responses = {
                    @ApiResponse(responseCode = "201", description= "CREATED", content = @Content(schema = @Schema(implementation = VotoResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description= "BAD REQUEST", content = {@Content()}),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    VotoResponseDTO criaVoto (@Valid @RequestBody VotoRequestDTO request);

    @PutMapping(value = "/public/v1/voto")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Voto", summary = "Atualiza o registro de um voto pelo id correspondente",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VotoResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description= "BAD REQUEST", content = {@Content()}),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    VotoResponseDTO atualizaVoto(@Valid @RequestBody VotoUpdateDTO update);

    @GetMapping(value = "/public/v1/voto")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(tags = "Voto", summary = "Busca uma lista com todos votos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description= "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VotoResponseDTO.class))),
            })
    CustomVotoCollectionDTO<VotoResponseDTO> buscaTodosVotos();

    @DeleteMapping(value = "/public/v1/voto/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(tags = "Voto", summary = "Deleta o voto de um eleitor com o id correspondente",
            responses = {
                    @ApiResponse(responseCode = "204", description= "NO CONTENT", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description= "NOT FOUND", content = {@Content()})
            })
    ResponseEntity<Void> deletaVoto (@PathVariable String id);

    @DeleteMapping(value = "/private/v1/voto")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(tags = "Voto", summary = "Deleta todos os registros da collection - somente testes",
            responses = {
                    @ApiResponse(responseCode = "204", description= "NO CONTENT", content = @Content(mediaType = "application/json")),
            })
    ResponseEntity<Void> zeraCollectionVoto ();
}
