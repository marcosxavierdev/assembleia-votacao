package com.marcosxavier.assembleia.voto.application.controller;

import com.marcosxavier.assembleia.voto.domain.dtos.VotoRequestDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface VotoAPI {

    @GetMapping(value = "/public/v1/voto/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    VotoResponseDTO buscaPorId(@PathVariable String id);

    @PostMapping(value = "/public/v1/voto")
    @ResponseStatus(code = HttpStatus.CREATED)
    VotoResponseDTO criaVoto (@Valid @RequestBody VotoRequestDTO request);

    @PutMapping(value = "/public/v1/voto")
    @ResponseStatus(code = HttpStatus.OK)
    VotoResponseDTO atualizaVoto(@Valid @RequestBody VotoUpdateDTO update);

    @GetMapping(value = "/public/v1/voto")
    @ResponseStatus(code = HttpStatus.OK)
    List<VotoResponseDTO> buscaTodosVotos();

    @DeleteMapping(value = "/public/v1/voto/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaVoto (@PathVariable String id);

    @DeleteMapping(value = "/private/v1/voto")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void zeraCollectionVoto ();
}
