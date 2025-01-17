package com.marcosxavier.assembleia.eleitor.application.controller;

import com.marcosxavier.assembleia.eleitor.application.service.EleitorService;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log4j2
@RequiredArgsConstructor
public class EleitorRestController implements EleitorAPI{

    private final EleitorService service;

    @Override
    public EleitorResponseDTO getEleitorById(String id) {
        return service.buscaPorId(id);
    }

    @Override
    public EleitorResponseDTO createEleitor(@Valid EleitorRequestDTO request) {
        return service.criaEleitor(request);
    }

    @Override
    public EleitorResponseDTO updateEleitor(@Valid EleitorUpdateDTO update) {
        return service.atualizaEleitor(update);
    }

    @Override
    public List<EleitorResponseDTO> getTodosEleitores() {
        return service.buscaTodosEleitores();
    }

    @Override
    public void deleteEleitor(String id) {
        service.deletaEleitor(id);
    }
}
