package com.marcosxavier.assembleia.pauta.service;

import com.marcosxavier.assembleia.pauta.assembler.PautaAssembler;
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
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada!"));
    }

    @Override
    public void zeraCollectionPauta() {
        repository.zeraCollectionPauta();
    }

    @Override
    public PautaResponseDTO encerraPauta(String id) {
        var pauta = buscaPautaPorId(id);
        pauta.setStatus(PautaStatusEnum.CLOSED);
        repository.salva(pauta);
        var pautaMapper= new PautaAssembler();
        return pautaMapper.toResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO buscaPorId(String id) {
        var pauta = buscaPautaPorId(id);
        var pautaMapper= new PautaAssembler();
        return pautaMapper.toResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        var pauta = new Pauta(request);
        repository.salva(pauta);
        return new PautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        log.info(update);
        Pauta pauta = buscaPautaPorId(update.getId());
        if (update.getAssunto() != null) {
            pauta.setAssunto(update.getAssunto());
        }
        if (update.getTempoMinutos() != null) {
            pauta.setTempoMinutos(update.getTempoMinutos());
        }
        log.info(pauta);
        repository.salva(pauta);
        return new PautaResponseDTO(pauta);
    }

    @Override
    public List<PautaResponseDTO> buscaTodasPautas() {
        return repository.buscaLista();
    }

    @Override
    public void deletaPauta(String id) {
        Pauta pauta = buscaPautaPorId(id);
        repository.deleta(pauta);
    }
}
