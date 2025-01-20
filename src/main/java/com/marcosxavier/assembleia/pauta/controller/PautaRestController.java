package com.marcosxavier.assembleia.pauta.controller;

import com.marcosxavier.assembleia.pauta.service.PautaService;
import com.marcosxavier.assembleia.pauta.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.dtos.PautaUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PautaRestController implements PautaAPI {

    private final PautaService service;

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
