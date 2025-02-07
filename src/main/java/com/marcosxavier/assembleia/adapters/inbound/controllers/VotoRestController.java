package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Voto;
import com.marcosxavier.assembleia.application.ports.in.api.VotoAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.VotoUsecase;
import com.marcosxavier.assembleia.domain.dto.voto.CustomVotoCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import com.marcosxavier.assembleia.utils.assemblers.VotoAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class VotoRestController implements VotoAPI {

    private final VotoUsecase service;
    private final VotoAssembler assembler;

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        log.info("VotoRestController - buscando voto: {}",id);
        var voto = service.buscaPorId(id);
        return assembler.toModelBuscaPorId(voto);
    }

    @Override
    public VotoResponseDTO criaVoto(@Valid VotoRequestDTO request) {
        log.info("EleitorRestController - criando voto");
        var voto = service.criaVoto(request);
        return assembler.toModelCriaVoto(voto);
    }

    @Override
    public VotoResponseDTO atualizaVoto(@Valid VotoUpdateDTO update) {
        log.info("EleitorRestController - atualizando voto");
        var voto = service.atualizaVoto(update);
        return assembler.toModelAtualizaVoto(voto);
    }

    @Override
    public CustomVotoCollectionDTO<VotoResponseDTO> buscaTodosVotos() {
        log.info("EleitorRestController - listando votos");
        List<Voto> votos = service.buscaTodosVotos();
        return assembler.toCollectionModel(votos);
    }

    @Override
    public ResponseEntity<Void> deletaVoto(String id) {
        log.info("VotoRestController - deletando um voto: {}",id);
        service.deletaVoto(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> zeraCollectionVoto() {
        log.info("VotoRestController - zerando a collection de voto");
        service.zeraCollectionVoto();
        return ResponseEntity.noContent().build();
    }
}
