package com.marcosxavier.assembleia.voto.application.controller;

import com.marcosxavier.assembleia.voto.domain.dtos.VotoRequestDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/v1/voto")
public interface VotoAPI {

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    VotoResponseDTO buscaPorId(@PathVariable String id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    VotoResponseDTO criaVoto (@Valid @RequestBody VotoRequestDTO request);

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    VotoResponseDTO atualizaVoto(@Valid @RequestBody VotoUpdateDTO update);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<VotoResponseDTO> buscaTodosVotos();

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaVoto (@PathVariable String id);
}
