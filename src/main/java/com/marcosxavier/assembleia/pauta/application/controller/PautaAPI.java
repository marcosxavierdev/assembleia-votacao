package com.marcosxavier.assembleia.pauta.application.controller;

import com.marcosxavier.assembleia.pauta.domain.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/v1/pauta")
public interface PautaAPI {

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    PautaResponseDTO buscaPorId(@PathVariable String id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PautaResponseDTO criaPauta (@Valid @RequestBody PautaRequestDTO request);

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    PautaResponseDTO atualizaPauta(@Valid @RequestBody PautaUpdateDTO update);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<PautaResponseDTO> buscaTodasPautas();

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaPauta (@PathVariable String id);
}
