package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.EleitorMongodbEntity;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.VotoMongodbEntity;
import com.marcosxavier.assembleia.application.ports.in.usecases.EleitorUsecase;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
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

    public VotoMongodbEntity buscaVotoPorId(String id) {
        log.info("VotoService - buscaVotoPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VotoMongodbEntity não encontrado!"));
    }

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        log.info("[iniciando]VotoService - buscaPorId: {}", id);
        var voto = buscaVotoPorId(id);
        log.info("[finalizando]VotoService - buscaPorId: {}", id);
        return VotoMapper.INSTANCE.toVotoResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO criaVoto(VotoRequestDTO request) {
        log.info("[iniciando]VotoService - criaVoto");
        validaVoto(request.getIdPauta(), request.getIdEleitor());
        var voto = new VotoMongodbEntity(request);
        repository.salva(voto);
        log.info("[finalizando]VotoService - criaVoto");
        return new VotoResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO atualizaVoto(VotoUpdateDTO update) {
        log.info("[iniciando]VotoService - atualizaVoto");
        VotoMongodbEntity votoMongodbEntity = buscaVotoPorId(update.getId());
        if (update.getIdPauta() != null) {
            votoMongodbEntity.setIdPauta(update.getIdPauta());
        }
        if (update.getIdEleitor() != null) {
            votoMongodbEntity.setIdEleitor(update.getIdEleitor());
        }
        if (update.getAprovacao() != null) {
            votoMongodbEntity.setAprovacao(update.getAprovacao());
        }
        repository.salva(votoMongodbEntity);
        log.info("[finalizando]VotoService - atualizaVoto");
        return new VotoResponseDTO(votoMongodbEntity);
    }

    @Override
    public List<VotoResponseDTO> buscaTodosVotos() {
        log.info("VotoService - buscaTodosVotos");
        return repository.buscaLista();
    }

    @Override
    public void deletaVoto(String id) {
        log.info("[iniciando]VotoService - deletaVoto: {}", id);
        VotoMongodbEntity votoMongodbEntity = buscaVotoPorId(id);
        repository.deleta(votoMongodbEntity);
        log.info("[finalizando]VotoService - deletaVoto: {}", id);
    }

    @Override
    public void zeraCollectionVoto() {
        log.info("[iniciando]VotoService - zeraCollectionVoto");
        repository.zeraCollectionVoto();
        log.info("[finalizando]VotoService - zeraCollectionVoto");
    }

    public List<VotoMongodbEntity> buscaTodasVotosPorIdPautaEIdEleitor(String  idPauta, String idEleitor) {
        log.info("VotoService - buscaTodasVotosPorIdPautaEIdEleitor: {} e {}", idPauta, idEleitor);
        return repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor);
    }

    private void validaVoto(String  idPauta, String idEleitor) {
        log.info("[iniciando]VotoService - validaVoto: {} e {}", idPauta, idEleitor);

        PautaMongodbEntity pautaMongodbEntity = pautaUsecase.buscaPautaPorId(idPauta);
        EleitorMongodbEntity eleitorMongodbEntity = eleitorUsecase.buscaEleitorPorId(idEleitor);
        log.info("[finalizando]VotoService - validaVoto");
        if (!repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "VotoMongodbEntity deste eleitorMongodbEntity já registrado nesta pautaMongodbEntity");
        }

        if (pautaMongodbEntity.getStatus().equals(PautaStatusEnum.CLOSED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PautaMongodbEntity já encerrada, não aceitamos mais votos");
        }
    }
}
