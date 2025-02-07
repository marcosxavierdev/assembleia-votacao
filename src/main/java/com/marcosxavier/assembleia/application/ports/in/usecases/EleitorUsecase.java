package com.marcosxavier.assembleia.application.ports.in.usecases;

import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorRequestDTO;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorUpdateDTO;
import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleitorUsecase {
    Eleitor buscaPorId(String id);
    Eleitor criaEleitor(EleitorRequestDTO request);
    Eleitor atualizaEleitor(EleitorUpdateDTO update);
    List<Eleitor> buscaTodosEleitores();
    void deletaEleitor(String id);
    Eleitor buscaPorCpf(String cpf);
    Eleitor buscaEleitorPorId(String id);
    void zeraCollectionEleitor();
}
