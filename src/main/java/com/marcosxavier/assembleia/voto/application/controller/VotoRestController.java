package com.marcosxavier.assembleia.voto.application.controller;


import com.marcosxavier.assembleia.voto.application.service.VotoService;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoRequestDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class VotoRestController implements VotoAPI {

    private final VotoService service;

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        return service.buscaPorId(id);
    }

    @Override
    public VotoResponseDTO criaVoto(@Valid VotoRequestDTO request) {
        return service.criaVoto(request);
    }

    @Override
    public VotoResponseDTO atualizaVoto(@Valid VotoUpdateDTO update) {
        return service.atualizaVoto(update);
    }

    @Override
    public List<VotoResponseDTO> buscaTodosVotos() {
        return service.buscaTodosVotos();
    }

    @Override
    public void deletaVoto(String id) {
        service.deletaVoto(id);
    }

    @Override
    public void zeraCollectionVoto() {
        service.zeraCollectionVoto();
    }
}
