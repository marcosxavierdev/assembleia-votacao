package com.marcosxavier.assembleia.pauta.assembler;

import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.entities.Pauta;

public class PautaAssembler {

    public PautaResponseDTO toResponseDTO(Pauta pauta){
        PautaResponseDTO response = new PautaResponseDTO();
        response.setId(pauta.getId());
        response.setAssunto(pauta.getAssunto());
        response.setTempoMinutos(pauta.getTempoMinutos());
        response.setStatus(pauta.getStatus());
        return response;
    }
}
