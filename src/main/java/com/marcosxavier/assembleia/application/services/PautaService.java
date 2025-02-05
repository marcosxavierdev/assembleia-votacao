package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
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

    public PautaMongodbEntity buscaPautaPorId(String id) {
        log.info("PautaService - buscaEleitorPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PautaMongodbEntity não encontrada!"));
    }

    @Override
    public void zeraCollectionPauta() {
        log.info("[iniciando]PautaService - zeraCollectionPauta");
        repository.zeraCollectionPauta();
        log.info("[finalizando]PautaService - zeraCollectionPauta");
    }

    @Override
    public PautaResponseDTO encerraPauta(String id) {
        log.info("[iniciando]PautaService - encerraPauta: {}", id);
        var pauta = buscaPautaPorId(id);
        pauta.setStatus(PautaStatusEnum.CLOSED);
        repository.salva(pauta);
        log.info("[finalizando]PautaService - encerraPauta: {}", id);
        return PautaMapper.INSTANCE.toPautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO buscaPorId(String id) {
        log.info("[iniciando]PautaService - buscaPorId: {}", id);
        var pauta = buscaPautaPorId(id);
        log.info("[finalizando]PautaService - buscaPorId: {}", id);
        return PautaMapper.INSTANCE.toPautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        log.info("[iniciando]PautaService - criaPauta");
        var pauta = new PautaMongodbEntity(request);
        repository.salva(pauta);
        log.info("[iniciando]PautaService - criaPauta");
        return new PautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        log.info("[iniciando]PautaService - atualizaPauta");
        PautaMongodbEntity pautaMongodbEntity = buscaPautaPorId(update.getId());
        if (update.getAssunto() != null) {
            pautaMongodbEntity.setAssunto(update.getAssunto());
        }
        if (update.getTempoMinutos() != null) {
            pautaMongodbEntity.setTempoMinutos(update.getTempoMinutos());
        }
        repository.salva(pautaMongodbEntity);
        log.info("[iniciando]PautaService - criaPauta");
        return new PautaResponseDTO(pautaMongodbEntity);
    }

    @Override
    public List<PautaResponseDTO> buscaTodasPautas() {
        log.info("PautaService - buscaTodasPautas");
        return repository.buscaLista();
    }

    @Override
    public void deletaPauta(String id) {
        log.info("[iniciando]PautaService - deletaPauta: {}", id);
        PautaMongodbEntity pautaMongodbEntity = buscaPautaPorId(id);
        repository.deleta(pautaMongodbEntity);
        log.info("[finalizando]PautaService - criaPauta: {}", id);
    }
}
