package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import com.marcosxavier.assembleia.application.ports.in.usecases.EleitorUsecase;
import com.marcosxavier.assembleia.utils.assemblers.EleitorAssembler;
import com.marcosxavier.assembleia.utils.mappers.EleitorMapper;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorRequestDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorUpdateDTO;
import com.marcosxavier.assembleia.utils.enums.EleitorStatusEnum;
import com.marcosxavier.assembleia.application.ports.out.repositories.EleitorRepository;
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
public class EleitorService implements EleitorUsecase {

    private final EleitorRepository repository;

    @Autowired
    CPFValidationService validadorCPF;

    public Eleitor buscaEleitorPorId(String id) {
        log.info("EleitorService - buscaEleitorPorId: {}", id);
        return repository.buscaPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public void zeraCollectionEleitor() {
        log.info("[iniciando]EleitorService - zeraCollectionEleitor");
        repository.zeraCollectionEleitor();
        log.info("[finalizando]EleitorService - zeraCollectionEleitor");
    }

    public Eleitor buscaEleitorPorCpf(String cpf) {
        log.info("EleitorService - buscaEleitorPorCpf: {}", cpf);
        return repository.buscaPorCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Eleitor não encontrado!"));
    }

    @Override
    public List<Eleitor> buscaTodosEleitores() {
        log.info("EleitorService - buscaTodosEleitores");
        return repository.buscaLista();
    }

    @Override
    public void deletaEleitor(String id) {
        log.info("[iniciando]EleitorService - deletaEleitor: {}", id);
        Eleitor eleitor = buscaEleitorPorId(id);
        repository.deleta(eleitor);
        log.info("[finalizando]EleitorService - deletaEleitor: {}", id);
    }

    @Override
    public Eleitor buscaPorId(String id) {
        log.info("[iniciando]EleitorService - buscaPorId: {}", id);
        var eleitor = buscaEleitorPorId(id);
        log.info("[finalizando]EleitorService - buscaPorId: {}", id);
        return eleitor;
    }

    @Override
    public Eleitor buscaPorCpf(String cpf) {
        log.info("[iniciando]EleitorService - buscaPorCpf: {}", cpf);
        var eleitor = buscaEleitorPorCpf(cpf);
        var eleitorMapper= new EleitorAssembler();
        log.info("[finalizando]EleitorService - buscaPorCpf: {}", cpf);
        return eleitor;
    }

    @Override
    public Eleitor criaEleitor(EleitorRequestDTO request) {
        log.info("[iniciando]EleitorService - criaEleitor");
        validaEleitor(request.getCpf());
        var eleitor = new Eleitor(request);
        eleitor.setStatus(EleitorStatusEnum.ABLE_TO_VOTE);
        repository.salva(eleitor);
        log.info("[finalizando]EleitorService - criaEleitor");
        return eleitor;
    }

    @Override
    public Eleitor atualizaEleitor(EleitorUpdateDTO update) {
        log.info("[iniciando]EleitorService - atualizaEleitor");
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
        log.info("[finalizando]EleitorService - atualizaEleitor");
        return eleitor;
    }

    private void validaEleitor(String cpf) {
        log.info("EleitorService - validaEleitor");

        if (!validadorCPF.consultaAPIValidaCPF(cpf)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNABLE_TO_VOTE: CPF inválido");
        }

        if (!repository.buscaListaPorCpf(cpf).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNABLE_TO_VOTE: CPF já registrado");
        }
    }
}
