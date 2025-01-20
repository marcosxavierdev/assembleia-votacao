package com.marcosxavier.assembleia.voto.service;

import com.marcosxavier.assembleia.eleitor.service.EleitorService;
import com.marcosxavier.assembleia.eleitor.entities.Eleitor;
import com.marcosxavier.assembleia.pauta.service.PautaService;
import com.marcosxavier.assembleia.pauta.entities.Pauta;
import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.voto.assembler.VotoAssembler;
import com.marcosxavier.assembleia.voto.assembler.VotoMapper;
import com.marcosxavier.assembleia.voto.dtos.VotoRequestDTO;
import com.marcosxavier.assembleia.voto.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.dtos.VotoUpdateDTO;
import com.marcosxavier.assembleia.voto.entities.Voto;
import com.marcosxavier.assembleia.voto.persistence.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository repository;
    private final EleitorService eleitorService;
    private final PautaService pautaService;

    public Voto buscaVotoPorId(String id) {
        log.info("VotoServiceImpl - buscaVotoPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto não encontrado!"));
    }

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        log.info("[iniciando]VotoServiceImpl - buscaPorId: {}", id);
        var voto = buscaVotoPorId(id);
        log.info("[finalizando]VotoServiceImpl - buscaPorId: {}", id);
        return VotoMapper.INSTANCE.toVotoResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO criaVoto(VotoRequestDTO request) {
        log.info("[iniciando]VotoServiceImpl - criaVoto");
        validaVoto(request.getIdPauta(), request.getIdEleitor());
        var voto = new Voto(request);
        repository.salva(voto);
        log.info("[finalizando]VotoServiceImpl - criaVoto");
        return new VotoResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO atualizaVoto(VotoUpdateDTO update) {
        log.info("[iniciando]VotoServiceImpl - atualizaVoto");
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
        log.info("[finalizando]VotoServiceImpl - atualizaVoto");
        return new VotoResponseDTO(voto);
    }

    @Override
    public List<VotoResponseDTO> buscaTodosVotos() {
        log.info("VotoServiceImpl - buscaTodosVotos");
        return repository.buscaLista();
    }

    @Override
    public void deletaVoto(String id) {
        log.info("[iniciando]VotoServiceImpl - deletaVoto: {}", id);
        Voto voto = buscaVotoPorId(id);
        repository.deleta(voto);
        log.info("[finalizando]VotoServiceImpl - deletaVoto: {}", id);
    }

    @Override
    public void zeraCollectionVoto() {
        log.info("[iniciando]VotoServiceImpl - zeraCollectionVoto");
        repository.zeraCollectionVoto();
        log.info("[finalizando]VotoServiceImpl - zeraCollectionVoto");
    }

    public List<Voto> buscaTodasVotosPorIdPautaEIdEleitor(String  idPauta, String idEleitor) {
        log.info("VotoServiceImpl - buscaTodasVotosPorIdPautaEIdEleitor: {} e {}", idPauta, idEleitor);
        return repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor);
    }

    private void validaVoto(String  idPauta, String idEleitor) {
        log.info("[iniciando]VotoServiceImpl - validaVoto: {} e {}", idPauta, idEleitor);

        Pauta pauta = pautaService.buscaPautaPorId(idPauta);
        Eleitor eleitor = eleitorService.buscaEleitorPorId(idEleitor);
        log.info("[finalizando]VotoServiceImpl - validaVoto");
        if (!repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Voto deste eleitor já registrado nesta pauta");
        }

        if (pauta.getStatus().equals(PautaStatusEnum.CLOSED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pauta já encerrada, não aceitamos mais votos");
        }
    }
}
