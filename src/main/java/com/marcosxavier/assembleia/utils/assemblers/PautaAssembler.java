package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;

public class PautaAssembler {

    public PautaResponseDTO toResponseDTO(PautaMongodbEntity pautaMongodbEntity){
        PautaResponseDTO response = new PautaResponseDTO();
        response.setId(pautaMongodbEntity.getId());
        response.setAssunto(pautaMongodbEntity.getAssunto());
        response.setTempoMinutos(pautaMongodbEntity.getTempoMinutos());
        response.setStatus(pautaMongodbEntity.getStatus());
        return response;
    }
}
