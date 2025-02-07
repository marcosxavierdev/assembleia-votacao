package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.utils.mappers.PautaMapper;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaRequestDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaUpdateDTO;
import com.marcosxavier.assembleia.utils.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.application.ports.out.repositories.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PautaService implements PautaUsecase {

    private final PautaRepository repository;

    public Pauta buscaPautaPorId(String id) {
        log.info("PautaService - buscaEleitorPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada!"));
    }

    @Override
    public void zeraCollectionPauta() {
        log.info("[iniciando]PautaService - zeraCollectionPauta");
        repository.zeraCollectionPauta();
        log.info("[finalizando]PautaService - zeraCollectionPauta");
    }

    @Override
    public Pauta encerraPauta(String id) {
        log.info("[iniciando]PautaService - encerraPauta: {}", id);
        var pauta = buscaPautaPorId(id);
        pauta.setStatus(PautaStatusEnum.CLOSED);
        repository.salva(pauta);
        log.info("[finalizando]PautaService - encerraPauta: {}", id);
        return pauta;
    }

    @Override
    public Pauta buscaPorId(String id) {
        log.info("[iniciando]PautaService - buscaPorId: {}", id);
        var pauta = buscaPautaPorId(id);
        log.info("[finalizando]PautaService - buscaPorId: {}", id);
        return pauta;
    }

    @Override
    public Pauta criaPauta(PautaRequestDTO request) {
        log.info("[iniciando]PautaService - criaPauta");
        var pauta = new Pauta(request);
        repository.salva(pauta);
        log.info("[iniciando]PautaService - criaPauta");
        return pauta;
    }

    @Override
    public Pauta atualizaPauta(PautaUpdateDTO update) {
        log.info("[iniciando]PautaService - atualizaPauta");
        Pauta pauta = buscaPautaPorId(update.getId());
        if (update.getAssunto() != null) {
            pauta.setAssunto(update.getAssunto());
        }
        if (update.getTempoMinutos() != null) {
            pauta.setTempoMinutos(update.getTempoMinutos());
        }
        repository.salva(pauta);
        log.info("[iniciando]PautaService - criaPauta");
        return pauta;
    }

    @Override
    public List<Pauta> buscaTodasPautas() {
        log.info("PautaService - buscaTodasPautas");
        return repository.buscaLista();
    }

    @Override
    public void deletaPauta(String id) {
        log.info("[iniciando]PautaService - deletaPauta: {}", id);
        Pauta pauta = buscaPautaPorId(id);
        repository.deleta(pauta);
        log.info("[finalizando]PautaService - criaPauta: {}", id);
    }
}
