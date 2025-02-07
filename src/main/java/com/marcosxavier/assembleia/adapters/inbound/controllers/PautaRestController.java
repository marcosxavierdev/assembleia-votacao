package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.application.ports.in.api.PautaAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.domain.dto.pauta.CustomPautaCollectionDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaRequestDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaUpdateDTO;
import com.marcosxavier.assembleia.utils.assemblers.PautaAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PautaRestController implements PautaAPI {

    private final PautaUsecase service;
    private final PautaAssembler assembler;

    @Override
    public PautaResponseDTO buscaPorId(String id) {
        log.info("PautaRestController - buscando id: {}",id);
        var pauta = service.buscaPorId(id);
        return assembler.toModelBuscaPorId(pauta);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        log.info("PautaRestController - criando pauta");
        var pauta =  service.criaPauta(request);
        return assembler.toModelCriaPauta(pauta);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        log.info("PautaRestController - atualizando pauta");
        var pauta =  service.atualizaPauta(update);
        return assembler.toModelAtualizaPauta(pauta);
    }

    @Override
    public CustomPautaCollectionDTO<PautaResponseDTO> buscaTodasPautas() {
        log.info("PautaRestController - listando pautas");
        List<Pauta> pautas = service.buscaTodasPautas();
        return assembler.toCollectionModel(pautas);
    }

    @Override
    public ResponseEntity<Void> deletaPauta(String id) {
        log.info("PautaRestController - deletando uma pauta: {}",id);
        service.deletaPauta(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> zeraCollectionPauta() {
        log.info("PautaRestController - zerando a collection de pauta");
        service.zeraCollectionPauta();
        return ResponseEntity.noContent().build();
    }

    @Override
    public PautaResponseDTO encerrarPauta(String id) {
        log.info("PautaRestController - encerrando uma pauta: {}",id);
        var pauta = service.encerraPauta(id);
        return assembler.toModelEncerraPauta(pauta);
    }
}
