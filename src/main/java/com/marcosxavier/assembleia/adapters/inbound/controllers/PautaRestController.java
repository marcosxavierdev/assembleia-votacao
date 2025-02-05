package com.marcosxavier.assembleia.adapters.inbound.controllers;

import com.marcosxavier.assembleia.application.ports.in.api.PautaAPI;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaRequestDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PautaRestController implements PautaAPI {

    private final PautaUsecase service;

    @Override
    public PautaResponseDTO buscaPorId(String id) {
        log.info("PautaRestController - buscando id: {}",id);
        return service.buscaPorId(id);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        log.info("PautaRestController - criando pauta");
        return service.criaPauta(request);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        log.info("PautaRestController - atualizando pauta");
        return service.atualizaPauta(update);
    }

    @Override
    public List<PautaResponseDTO> buscaTodasPautas() {
        log.info("PautaRestController - listando pautas");
        return service.buscaTodasPautas();
    }

    @Override
    public void deletaPauta(String id) {
        log.info("PautaRestController - deletando uma pauta: {}",id);
        service.deletaPauta(id);
    }

    @Override
    public void zeraCollectionPauta() {
        log.info("PautaRestController - zerando a collection de pauta");
        service.zeraCollectionPauta();
    }

    @Override
    public PautaResponseDTO encerrarPauta(String id) {
        log.info("PautaRestController - encerrando uma pauta: {}",id);
        return service.encerraPauta(id);
    }
}
