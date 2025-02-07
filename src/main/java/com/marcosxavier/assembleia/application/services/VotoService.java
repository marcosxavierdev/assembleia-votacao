package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Voto;
import com.marcosxavier.assembleia.application.ports.in.usecases.EleitorUsecase;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.application.ports.in.usecases.VotoUsecase;
import com.marcosxavier.assembleia.utils.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.utils.mappers.VotoMapper;
import com.marcosxavier.assembleia.domain.dto.voto.VotoRequestDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import com.marcosxavier.assembleia.domain.dto.voto.VotoUpdateDTO;
import com.marcosxavier.assembleia.application.ports.out.repositories.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class VotoService implements VotoUsecase {

    private final VotoRepository repository;
    private final EleitorUsecase eleitorUsecase;
    private final PautaUsecase pautaUsecase;

    public Voto buscaVotoPorId(String id) {
        log.info("VotoService - buscaVotoPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto não encontrado!"));
    }

    @Override
    public Voto buscaPorId(String id) {
        log.info("[iniciando]VotoService - buscaPorId: {}", id);
        var voto = buscaVotoPorId(id);
        log.info("[finalizando]VotoService - buscaPorId: {}", id);
        return voto;
    }

    @Override
    public Voto criaVoto(VotoRequestDTO request) {
        log.info("[iniciando]VotoService - criaVoto");
        validaVoto(request.getIdPauta(), request.getIdEleitor());
        var voto = new Voto(request);
        repository.salva(voto);
        log.info("[finalizando]VotoService - criaVoto");
        return voto;
    }

    @Override
    public Voto atualizaVoto(VotoUpdateDTO update) {
        log.info("[iniciando]VotoService - atualizaVoto");
        Voto voto = buscaVotoPorId(update.getId());
        if (update.getIdPauta() != null) {
            voto.setIdPauta(update.getIdPauta());
        }
        if (update.getIdEleitor() != null) {
            voto.setIdEleitor(update.getIdEleitor());
        }
        if (update.getAprovacao() != null) {
            voto.setAprovacao(update.getAprovacao());
        }
        repository.salva(voto);
        log.info("[finalizando]VotoService - atualizaVoto");
        return voto;
    }

    @Override
    public List<Voto> buscaTodosVotos() {
        log.info("VotoService - buscaTodosVotos");
        return repository.buscaLista();
    }

    @Override
    public void deletaVoto(String id) {
        log.info("[iniciando]VotoService - deletaVoto: {}", id);
        Voto voto = buscaVotoPorId(id);
        repository.deleta(voto);
        log.info("[finalizando]VotoService - deletaVoto: {}", id);
    }

    @Override
    public void zeraCollectionVoto() {
        log.info("[iniciando]VotoService - zeraCollectionVoto");
        repository.zeraCollectionVoto();
        log.info("[finalizando]VotoService - zeraCollectionVoto");
    }

    public List<Voto> buscaTodasVotosPorIdPautaEIdEleitor(String  idPauta, String idEleitor) {
        log.info("VotoService - buscaTodasVotosPorIdPautaEIdEleitor: {} e {}", idPauta, idEleitor);
        return repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor);
    }

    private void validaVoto(String  idPauta, String idEleitor) {
        log.info("[iniciando]VotoService - validaVoto: {} e {}", idPauta, idEleitor);

        Pauta pauta = pautaUsecase.buscaPautaPorId(idPauta);
        Eleitor eleitor = eleitorUsecase.buscaEleitorPorId(idEleitor);
        log.info("[finalizando]VotoService - validaVoto");
        if (!repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Voto deste eleitor já registrado nesta pauta");
        }

        if (pauta.getStatus().equals(PautaStatusEnum.CLOSED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pauta já encerrada, não aceitamos mais votos");
        }
    }
}
