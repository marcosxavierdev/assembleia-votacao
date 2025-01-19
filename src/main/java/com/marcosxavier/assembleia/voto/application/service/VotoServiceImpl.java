package com.marcosxavier.assembleia.voto.application.service;

import com.marcosxavier.assembleia.eleitor.application.service.EleitorService;
import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import com.marcosxavier.assembleia.pauta.application.service.PautaService;
import com.marcosxavier.assembleia.pauta.domain.entities.Pauta;
import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.voto.assembler.VotoAssembler;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoRequestDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.domain.dtos.VotoUpdateDTO;
import com.marcosxavier.assembleia.voto.domain.entities.Voto;
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
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto não encontrado!"));
    }

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        var voto = buscaVotoPorId(id);
        var votoMapper= new VotoAssembler();
        return votoMapper.toResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO criaVoto(VotoRequestDTO request) {
        validaVoto(request.getIdPauta(), request.getIdEleitor());
        var voto = new Voto(request);
        repository.salva(voto);
        return new VotoResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO atualizaVoto(VotoUpdateDTO update) {
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
        return new VotoResponseDTO(voto);
    }

    @Override
    public List<VotoResponseDTO> buscaTodosVotos() {
        return repository.buscaLista();
    }

    @Override
    public void deletaVoto(String id) {
        Voto voto = buscaVotoPorId(id);
        repository.deleta(voto);
    }

    @Override
    public void zeraCollectionVoto() {
        repository.zeraCollectionVoto();
    }

    public List<Voto> buscaTodasVotosPorIdPautaEIdEleitor(String  idPauta, String idEleitor) {
        return repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor);
    }

    private void validaVoto(String  idPauta, String idEleitor) {

        Pauta pauta = pautaService.buscaPautaPorId(idPauta);
        Eleitor eleitor = eleitorService.buscaEleitorPorId(idEleitor);

        if (!repository.buscaTodasVotosPorIdPautaEIdEleitor(idPauta, idEleitor).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR_ELECTOR_ALREADY_VOTED_FOR_THIS_SURVEY");
        }

        if (pauta.getStatus().equals(PautaStatusEnum.CLOSED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR_THIS_SURVEY_IS_EXPIRED");
        }
    }
}
