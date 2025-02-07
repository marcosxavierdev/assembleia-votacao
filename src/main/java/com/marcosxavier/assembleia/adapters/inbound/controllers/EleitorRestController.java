package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import com.marcosxavier.assembleia.application.ports.in.api.EleitorAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.EleitorUsecase;
import com.marcosxavier.assembleia.domain.dto.eleitor.CustomEleitorCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorRequestDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorUpdateDTO;
import com.marcosxavier.assembleia.utils.assemblers.EleitorAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EleitorRestController implements EleitorAPI {

    private final EleitorUsecase service;
    private final EleitorAssembler assembler;

    @Override
    public EleitorResponseDTO buscaPorId(String id) {
        log.info("EleitorRestController - buscando id: {}",id);
        var eleitor = service.buscaPorId(id);
        return assembler.toModelBuscaPorId(eleitor);
    }

    @Override
    public EleitorResponseDTO buscaPorCpf(String cpf) {
        log.info("EleitorRestController - buscando cpf: {}",cpf);
        var eleitor = service.buscaPorCpf(cpf);
        return assembler.toModelBuscaPorCpf(eleitor);
    }

    @Override
    public EleitorResponseDTO criaEleitor(@Valid EleitorRequestDTO request) {
        log.info("EleitorRestController - criando eleitor");
        var eleitor =  service.criaEleitor(request);
        return assembler.toModelCriaEleitor(eleitor);
    }

    @Override
    public EleitorResponseDTO atualizaEleitor(@Valid EleitorUpdateDTO update) {
        log.info("EleitorRestController - atualizando eleitor");
        var eleitor =   service.atualizaEleitor(update);
        return assembler.toModelAtualizaEleitor(eleitor);
    }

    @Override
    public CustomEleitorCollectionDTO<EleitorResponseDTO> buscaTodosEleitores() {
        log.info("EleitorRestController - listando eleitores");
        List<Eleitor> eleitores = service.buscaTodosEleitores();
        return assembler.toCollectionModel(eleitores);
    }

    @Override
    public ResponseEntity<Void> deletaEleitor(String id) {
        log.info("EleitorRestController - deletando um eleitor: {}",id);
        service.deletaEleitor(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> zeraCollectionEleitor() {
        log.info("EleitorRestController - zerando a collection de eleitor");
        service.zeraCollectionEleitor();
        return ResponseEntity.noContent().build();
    }
}
