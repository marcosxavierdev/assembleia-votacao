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

import static java.util.Objects.isNull;

@Service
@Log4j2
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService{

    private final EleitorRepository repository;

    public Eleitor buscaEleitorPorId(String id) {
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public void zeraCollectionEleitor() {
        repository.zeraCollectionEleitor();
    }

    public Eleitor buscaEleitorPorCpf(String cpf) {
        return repository.buscaPorCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public List<EleitorResponseDTO> buscaTodosEleitores() {
        return repository.buscaLista();
    }

    @Override
    public void deletaEleitor(String id) {
        Eleitor eleitor = buscaEleitorPorId(id);
        repository.deleta(eleitor);
    }

    @Override
    public EleitorResponseDTO buscaPorId(String id) {
        var eleitor = buscaEleitorPorId(id);
        var eleitorMapper= new EleitorAssembler();
        return eleitorMapper.toResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO buscaPorCpf(String cpf) {
        var eleitor = buscaEleitorPorCpf(cpf);
        var eleitorMapper= new EleitorAssembler();
        return eleitorMapper.toResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO criaEleitor(EleitorRequestDTO request) {
        validaEleitor(request.getCpf());
        var eleitor = new Eleitor(request);
        eleitor.setStatus(EleitorStatusEnum.ABLE_TO_VOTE);
        repository.salva(eleitor);
        return new EleitorResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO atualizaEleitor(EleitorUpdateDTO update) {
        validaEleitor(update.getCpf());
        Eleitor eleitor = buscaEleitorPorId(update.getId());
        if (update.getId() != null) {
            eleitor.setId(update.getId());
        }
        if (update.getCpf() != null) {
            eleitor.setCpf(update.getCpf());
        }
        if (update.getStatus() != null) {
            eleitor.setStatus(update.getStatus());
        }
        repository.salva(eleitor);
        return new EleitorResponseDTO(eleitor);
    }

    private void validaEleitor(String cpf) {

        if (!ValidadorCPF.cpfValido(cpf)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNABLE_TO_VOTE: CPF inválido");
        }

        if (!repository.buscaListaPorCpf(cpf).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNABLE_TO_VOTE: CPF já registrado");
        }
    }
}
