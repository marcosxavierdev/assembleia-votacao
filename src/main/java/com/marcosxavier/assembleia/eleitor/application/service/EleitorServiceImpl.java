package com.marcosxavier.assembleia.eleitor.application.service;

import com.marcosxavier.assembleia.eleitor.assembler.EleitorAssembler;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.domain.dtos.EleitorUpdateDTO;
import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
import com.marcosxavier.assembleia.eleitor.persistence.repository.EleitorRepository;
import com.marcosxavier.assembleia.global.utils.ValidadorCPF;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService{

    private final EleitorRepository repository;

    public Eleitor findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public List<EleitorResponseDTO> buscaTodosEleitores() {
        return repository.findAll();
    }

    @Override
    public void deletaEleitor(String id) {
        Eleitor eleitor = findById(id);
        repository.delete(eleitor);
    }

    @Override
    public EleitorResponseDTO buscaPorId(String id) {
        var eleitor = findById(id);
        var eleitorMapper= new EleitorAssembler();
        return eleitorMapper.toResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO criaEleitor(EleitorRequestDTO request) {
        var eleitor = validaEleitor(request);
        repository.save(eleitor);
        return new EleitorResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO atualizaEleitor(EleitorUpdateDTO update) {
        Eleitor eleitor = findById(update.getId());
        if (update.getCpf() != null) {
            eleitor.setCpf(update.getCpf());
        }
        if (update.getStatus() != null) {
            eleitor.setStatus(update.getStatus());
        }
        repository.save(eleitor);
        return new EleitorResponseDTO(eleitor);
    }

    private Eleitor validaEleitor(EleitorRequestDTO request) {

        var eleitor = new Eleitor(request);
        if (!ValidadorCPF.cpfValido(request.getCpf())) {
            eleitor.setStatus(EleitorStatusEnum.UNABLE_TO_VOTE);
            return  eleitor;
        }

        if (!repository.findAllByCpf(request.getCpf()).isEmpty()) {
            eleitor.setStatus(EleitorStatusEnum.UNABLE_TO_VOTE);
            return  eleitor;
        }
        eleitor.setStatus(EleitorStatusEnum.ABLE_TO_VOTE);
        return eleitor;
    }
}
