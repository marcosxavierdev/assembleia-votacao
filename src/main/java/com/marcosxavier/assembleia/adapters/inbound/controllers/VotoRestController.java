package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.application.ports.in.api.VotoAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.VotoUsecase;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class VotoRestController implements VotoAPI {

    private final VotoUsecase service;

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        log.info("VotoRestController - buscando voto: {}",id);
        return service.buscaPorId(id);
    }

    @Override
    public VotoResponseDTO criaVoto(@Valid VotoRequestDTO request) {
        log.info("EleitorRestController - criando voto");
        return service.criaVoto(request);
    }

    @Override
    public VotoResponseDTO atualizaVoto(@Valid VotoUpdateDTO update) {
        log.info("EleitorRestController - atualizando voto");
        return service.atualizaVoto(update);
    }

    @Override
    public List<VotoResponseDTO> buscaTodosVotos() {
        log.info("EleitorRestController - listando votos");
        return service.buscaTodosVotos();
    }

    @Override
    public void deletaVoto(String id) {
        log.info("VotoRestController - deletando um voto: {}",id);
        service.deletaVoto(id);
    }

    @Override
    public void zeraCollectionVoto() {
        log.info("VotoRestController - zerando a collection de voto");
        service.zeraCollectionVoto();
    }
}
