package com.marcosxavier.assembleia.eleitor.controller;

import com.marcosxavier.assembleia.eleitor.service.EleitorService;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorUpdateDTO;
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
    public EleitorResponseDTO buscaPorId(String id) {
        return service.buscaPorId(id);
    }

    @Override
    public EleitorResponseDTO buscaPorCpf(String cpf) {
        return service.buscaPorCpf(cpf);
    }

    @Override
    public EleitorResponseDTO criaEleitor(@Valid EleitorRequestDTO request) {
        return service.criaEleitor(request);
    }

    @Override
    public EleitorResponseDTO atualizaEleitor(@Valid EleitorUpdateDTO update) {
        return service.atualizaEleitor(update);
    }

    @Override
    public List<EleitorResponseDTO> buscaTodosEleitores() {
        return service.buscaTodosEleitores();
    }

    @Override
    public void deletaEleitor(String id) {
        service.deletaEleitor(id);
    }

    @Override
    public void zeraCollectionEleitor() {
        service.zeraCollectionEleitor();
    }
}
