package com.marcosxavier.assembleia.pauta.service;

import com.marcosxavier.assembleia.pauta.assembler.PautaMapper;
import com.marcosxavier.assembleia.pauta.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.dtos.PautaUpdateDTO;
import com.marcosxavier.assembleia.pauta.entities.Pauta;
import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.pauta.persistence.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService{

    private final PautaRepository repository;

    public Pauta buscaPautaPorId(String id) {
        log.info("PautaServiceImpl - buscaEleitorPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada!"));
    }

    @Override
    public void zeraCollectionPauta() {
        log.info("[iniciando]PautaServiceImpl - zeraCollectionPauta");
        repository.zeraCollectionPauta();
        log.info("[finalizando]PautaServiceImpl - zeraCollectionPauta");
    }

    @Override
    public PautaResponseDTO encerraPauta(String id) {
        log.info("[iniciando]PautaServiceImpl - encerraPauta: {}", id);
        var pauta = buscaPautaPorId(id);
        pauta.setStatus(PautaStatusEnum.CLOSED);
        repository.salva(pauta);
        log.info("[finalizando]PautaServiceImpl - encerraPauta: {}", id);
        return PautaMapper.INSTANCE.toPautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO buscaPorId(String id) {
        log.info("[iniciando]PautaServiceImpl - buscaPorId: {}", id);
        var pauta = buscaPautaPorId(id);
        log.info("[finalizando]PautaServiceImpl - buscaPorId: {}", id);
        return PautaMapper.INSTANCE.toPautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        log.info("[iniciando]PautaServiceImpl - criaPauta");
        var pauta = new Pauta(request);
        repository.salva(pauta);
        log.info("[iniciando]PautaServiceImpl - criaPauta");
        return new PautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        log.info("[iniciando]PautaServiceImpl - atualizaPauta");
        Pauta pauta = buscaPautaPorId(update.getId());
        if (update.getAssunto() != null) {
            pauta.setAssunto(update.getAssunto());
        }
        if (update.getTempoMinutos() != null) {
            pauta.setTempoMinutos(update.getTempoMinutos());
        }
        repository.salva(pauta);
        log.info("[iniciando]PautaServiceImpl - criaPauta");
        return new PautaResponseDTO(pauta);
    }

    @Override
    public List<PautaResponseDTO> buscaTodasPautas() {
        log.info("PautaServiceImpl - buscaTodasPautas");
        return repository.buscaLista();
    }

    @Override
    public void deletaPauta(String id) {
        log.info("[iniciando]PautaServiceImpl - deletaPauta: {}", id);
        Pauta pauta = buscaPautaPorId(id);
        repository.deleta(pauta);
        log.info("[finalizando]PautaServiceImpl - criaPauta: {}", id);
    }
}
