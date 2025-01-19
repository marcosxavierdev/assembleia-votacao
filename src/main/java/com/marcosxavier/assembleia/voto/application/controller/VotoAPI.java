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
    VotoResponseDTO getVotoById(@PathVariable String id);

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    VotoResponseDTO createVoto (@Valid @RequestBody VotoRequestDTO request);

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    VotoResponseDTO updateVoto(@Valid @RequestBody VotoUpdateDTO update);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<VotoResponseDTO> getTodosVotos();

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteVoto (@PathVariable String id);
}
