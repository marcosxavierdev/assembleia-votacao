package com.marcosxavier.assembleia.pauta.application.controller;

import com.marcosxavier.assembleia.pauta.domain.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface PautaAPI {

    @GetMapping(value = "/public/v1/pauta/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    PautaResponseDTO buscaPorId(@PathVariable String id);

    @PostMapping(value = "/public/v1/pauta")
    @ResponseStatus(code = HttpStatus.CREATED)
    PautaResponseDTO criaPauta (@Valid @RequestBody PautaRequestDTO request);

    @PutMapping(value = "/public/v1/pauta")
    @ResponseStatus(code = HttpStatus.OK)
    PautaResponseDTO atualizaPauta(@Valid @RequestBody PautaUpdateDTO update);

    @GetMapping(value = "/public/v1/pauta")
    @ResponseStatus(code = HttpStatus.OK)
    List<PautaResponseDTO> buscaTodasPautas();

    @DeleteMapping(value = "/public/v1/pauta/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaPauta (@PathVariable String id);

    @DeleteMapping(value = "/private/v1/pauta")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void zeraCollectionPauta ();

    @PostMapping(value = "/public/v1/pauta/{id}/encerrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    PautaResponseDTO encerrarPauta (@PathVariable String id);
}
