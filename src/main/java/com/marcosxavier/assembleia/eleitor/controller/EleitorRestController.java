package com.marcosxavier.assembleia.eleitor.controller;

import com.marcosxavier.assembleia.eleitor.service.EleitorService;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EleitorRestController implements EleitorAPI{

    private final EleitorService service;

    @Override
    public EleitorResponseDTO buscaPorId(String id) {
        log.info("EleitorRestController - buscando id: {}",id);
        return service.buscaPorId(id);
    }

    @Override
    public EleitorResponseDTO buscaPorCpf(String cpf) {
        log.info("EleitorRestController - buscando cpf: {}",cpf);
        return service.buscaPorCpf(cpf);
    }

    @Override
    public EleitorResponseDTO criaEleitor(@Valid EleitorRequestDTO request) {
        log.info("EleitorRestController - criando eleitor");
        return service.criaEleitor(request);
    }

    @Override
    public EleitorResponseDTO atualizaEleitor(@Valid EleitorUpdateDTO update) {
        log.info("EleitorRestController - atualizando eleitor");
        return service.atualizaEleitor(update);
    }

    @Override
    public List<EleitorResponseDTO> buscaTodosEleitores() {
        log.info("EleitorRestController - listando eleitores");
        return service.buscaTodosEleitores();
    }

    @Override
    public void deletaEleitor(String id) {
        log.info("EleitorRestController - deletando um eleitor: {}",id);
        service.deletaEleitor(id);
    }

    @Override
    public void zeraCollectionEleitor() {
        log.info("EleitorRestController - zerando a collection de eleitor");
        service.zeraCollectionEleitor();
    }
}
