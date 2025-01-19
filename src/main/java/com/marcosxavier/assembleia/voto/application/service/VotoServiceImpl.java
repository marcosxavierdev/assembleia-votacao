package com.marcosxavier.assembleia.voto.application.service;

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

    public Voto findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto não encontrado!"));
    }

    @Override
    public VotoResponseDTO buscaPorId(String id) {
        var voto = findById(id);
        var votoMapper= new VotoAssembler();
        return votoMapper.toResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO criaVoto(VotoRequestDTO request) {
        var voto = new Voto(request);
        repository.save(voto);
        return new VotoResponseDTO(voto);
    }

    @Override
    public VotoResponseDTO atualizaVoto(VotoUpdateDTO update) {
        log.info("in"+update);
        Voto voto = findById(update.getId());
        if (update.getIdPauta() != null) {
            voto.setIdPauta(update.getIdPauta());
        }
        if (update.getIdEleitor() != null) {
            voto.setIdEleitor(update.getIdEleitor());
        }
        if (update.getAprovacao() != null) {
            voto.setAprovacao(update.getAprovacao());
        }
        log.info("ou"+voto);
        repository.save(voto);
        return new VotoResponseDTO(voto);
    }

    @Override
    public List<VotoResponseDTO> buscaTodosVotos() {
        return repository.findAll();
    }

    @Override
    public void deletaVoto(String id) {
        Voto voto = findById(id);
        repository.delete(voto);
    }
}
