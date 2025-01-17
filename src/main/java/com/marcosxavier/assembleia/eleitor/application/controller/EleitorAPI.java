package com.marcosxavier.assembleia.eleitor.application.controller;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorUpdateDTO;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping(value="/public/v1/eleitor")
public interface EleitorAPI {

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    EleitorResponseDTO getEleitorById(@PathVariable String id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    EleitorResponseDTO createEleitor (@Valid @RequestBody EleitorRequestDTO request);

    @PutMapping
    EleitorResponseDTO updateEleitor(@Valid @RequestBody EleitorUpdateDTO update);

    @GetMapping
    List<EleitorResponseDTO> getTodosEleitores();

    @DeleteMapping("/{id}")
    void deleteEleitor (@PathVariable String id);
}
