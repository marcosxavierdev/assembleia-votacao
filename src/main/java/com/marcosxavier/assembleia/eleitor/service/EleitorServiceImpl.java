package com.marcosxavier.assembleia.eleitor.service;

import com.marcosxavier.assembleia.eleitor.assembler.EleitorAssembler;
import com.marcosxavier.assembleia.eleitor.assembler.EleitorMapper;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorRequestDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.dtos.EleitorUpdateDTO;
import com.marcosxavier.assembleia.eleitor.entities.Eleitor;
import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
import com.marcosxavier.assembleia.eleitor.persistence.repository.EleitorRepository;
import com.marcosxavier.assembleia.global.utils.validadorCPF.ValidadorCPF;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class EleitorServiceImpl implements EleitorService{

    private final EleitorRepository repository;

    @Autowired
    ValidadorCPF validadorCPF;

    public Eleitor buscaEleitorPorId(String id) {
        log.info("EleitorServiceImpl - buscaEleitorPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public void zeraCollectionEleitor() {
        log.info("[iniciando]EleitorServiceImpl - zeraCollectionEleitor");
        repository.zeraCollectionEleitor();
        log.info("[finalizando]EleitorServiceImpl - zeraCollectionEleitor");
    }

    public Eleitor buscaEleitorPorCpf(String cpf) {
        log.info("EleitorServiceImpl - buscaEleitorPorCpf: {}", cpf);
        return repository.buscaPorCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public List<EleitorResponseDTO> buscaTodosEleitores() {
        log.info("EleitorServiceImpl - buscaTodosEleitores");
        return repository.buscaLista();
    }

    @Override
    public void deletaEleitor(String id) {
        log.info("[iniciando]EleitorServiceImpl - deletaEleitor: {}", id);
        Eleitor eleitor = buscaEleitorPorId(id);
        repository.deleta(eleitor);
        log.info("[finalizando]EleitorServiceImpl - deletaEleitor: {}", id);
    }

    @Override
    public EleitorResponseDTO buscaPorId(String id) {
        log.info("[iniciando]EleitorServiceImpl - buscaPorId: {}", id);
        var eleitor = buscaEleitorPorId(id);
        log.info("[finalizando]EleitorServiceImpl - buscaPorId: {}", id);
        return EleitorMapper.INSTANCE.toEleitorResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO buscaPorCpf(String cpf) {
        log.info("[iniciando]EleitorServiceImpl - buscaPorCpf: {}", cpf);
        var eleitor = buscaEleitorPorCpf(cpf);
        var eleitorMapper= new EleitorAssembler();
        log.info("[finalizando]EleitorServiceImpl - buscaPorCpf: {}", cpf);
        return eleitorMapper.toResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO criaEleitor(EleitorRequestDTO request) {
        log.info("[iniciando]EleitorServiceImpl - criaEleitor");
        validaEleitor(request.getCpf());
        var eleitor = new Eleitor(request);
        eleitor.setStatus(EleitorStatusEnum.ABLE_TO_VOTE);
        repository.salva(eleitor);
        log.info("[finalizando]EleitorServiceImpl - criaEleitor");
        return new EleitorResponseDTO(eleitor);
    }

    @Override
    public EleitorResponseDTO atualizaEleitor(EleitorUpdateDTO update) {
        log.info("[iniciando]EleitorServiceImpl - atualizaEleitor");
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
        log.info("[finalizando]EleitorServiceImpl - atualizaEleitor");
        return new EleitorResponseDTO(eleitor);
    }

    private void validaEleitor(String cpf) {
        log.info("EleitorServiceImpl - validaEleitor");

        if (!validadorCPF.consulaAPIValidaCPF(cpf)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNABLE_TO_VOTE: CPF inválido");
        }

        if (!repository.buscaListaPorCpf(cpf).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNABLE_TO_VOTE: CPF já registrado");
        }
    }
}
