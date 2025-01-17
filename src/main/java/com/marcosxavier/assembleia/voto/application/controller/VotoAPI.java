package com.marcosxavier.assembleia.voto.application.controller;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorUpdateDTO;
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
    VotoResponseDTO getVotoById(@PathVariable String id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    VotoResponseDTO createVoto (@Valid @RequestBody VotoRequestDTO request);

    @PutMapping
    VotoResponseDTO updateVoto(@Valid @RequestBody VotoUpdateDTO update);

    @GetMapping
    List<VotoResponseDTO> getTodosVotos();

    @DeleteMapping("/{id}")
    void deleteVoto (@PathVariable String id);
}
