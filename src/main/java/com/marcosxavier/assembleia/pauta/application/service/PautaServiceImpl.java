package com.marcosxavier.assembleia.pauta.application.service;

import com.marcosxavier.assembleia.pauta.assembler.PautaAssembler;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaRequestDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaUpdateDTO;
import com.marcosxavier.assembleia.pauta.domain.entities.Pauta;
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

    public Pauta findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada!"));
    }

    @Override
    public PautaResponseDTO buscaPorId(String id) {
        var pauta = findById(id);
        var pautaMapper= new PautaAssembler();
        return pautaMapper.toResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO criaPauta(PautaRequestDTO request) {
        var pauta = new Pauta(request);
        repository.save(pauta);
        return new PautaResponseDTO(pauta);
    }

    @Override
    public PautaResponseDTO atualizaPauta(PautaUpdateDTO update) {
        log.info(update);
        Pauta pauta = findById(update.getId());
        if (update.getAssunto() != null) {
            pauta.setAssunto(update.getAssunto());
        }
        if (update.getTempoMinutos() != null) {
            pauta.setTempoMinutos(update.getTempoMinutos());
        }
        log.info(pauta);
        repository.save(pauta);
        return new PautaResponseDTO(pauta);
    }

    @Override
    public List<PautaResponseDTO> buscaTodasPautas() {
        return repository.findAll();
    }

    @Override
    public void deletaPauta(String id) {
        Pauta pauta = findById(id);
        repository.delete(pauta);
    }
}
