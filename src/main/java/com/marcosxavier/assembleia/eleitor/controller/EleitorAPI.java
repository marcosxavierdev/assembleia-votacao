package com.marcosxavier.assembleia.eleitor.controller;

import com.marcosxavier.assembleia.eleitor.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorUpdateDTO;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface EleitorAPI {

    @GetMapping(value = "/public/v1/eleitor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    EleitorResponseDTO buscaPorId(@PathVariable String id);

    @GetMapping(value = "/byCPF/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    EleitorResponseDTO buscaPorCpf(@PathVariable String cpf);

    @PostMapping(value = "/public/v1/eleitor")
    @ResponseStatus(code = HttpStatus.CREATED)
    EleitorResponseDTO criaEleitor (@Valid @RequestBody EleitorRequestDTO request);

    @PutMapping(value = "/public/v1/eleitor")
    @ResponseStatus(code = HttpStatus.OK)
    EleitorResponseDTO atualizaEleitor(@Valid @RequestBody EleitorUpdateDTO update);

    @GetMapping(value = "/public/v1/eleitor")
    @ResponseStatus(code = HttpStatus.OK)
    List<EleitorResponseDTO> buscaTodosEleitores();

    @DeleteMapping(value = "/public/v1/eleitor/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaEleitor (@PathVariable String id);

    @DeleteMapping(value = "/private/v1/eleitor")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void zeraCollectionEleitor ();
}
