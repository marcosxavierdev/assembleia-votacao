package com.marcosxavier.assembleia.eleitor.application.controller;

import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/public/v1/eleitor")
public interface EleitorAPI {

//    @GetMapping(value = "/{id}")
//    @ResponseStatus(code = HttpStatus.OK)
//    EleitorResponseDTO findById(@PathVariable Long id);
//
//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED)
//    EleitorResponseDTO create(@Valid @RequestBody EleitorRequestDTO request);
//
//    @PutMapping
//    EleitorResponseDTO update(@Valid @RequestBody EleitorUpdateDTO update);
//
//    @GetMapping
//    Page<EleitorResponseDTO> findAll(@RequestParam(required = false) Optional<String> cpf,
//                                     @RequestParam(defaultValue = "0") Integer page,
//                                     @RequestParam(defaultValue = "10") Integer size,
//                                     @RequestParam(defaultValue = "id") String sort,
//                                     @RequestParam(defaultValue = "DESC") Sort.Direction direction);
//
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable Long id);
}
