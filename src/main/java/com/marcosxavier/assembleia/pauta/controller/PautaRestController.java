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
        return service.buscaPorId(id);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        return service.criaPauta(request);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        return service.atualizaPauta(update);
    }

    @Override
    public List<PautaResponseDTO> buscaTodasPautas() {
        return service.buscaTodasPautas();
    }

    @Override
    public void deletaPauta(String id) {
        service.deletaPauta(id);
    }

    @Override
    public void zeraCollectionPauta() {
        service.zeraCollectionPauta();
    }

    @Override
    public PautaResponseDTO encerrarPauta(String id) {
        return service.encerraPauta(id);
    }
}
