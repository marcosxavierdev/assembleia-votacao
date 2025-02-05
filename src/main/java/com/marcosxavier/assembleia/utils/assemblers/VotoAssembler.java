package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.VotoMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;

public class VotoAssembler {

    public VotoResponseDTO toResponseDTO(VotoMongodbEntity votoMongodbEntity){
        VotoResponseDTO response = new VotoResponseDTO();
        response.setId(votoMongodbEntity.getId());
        response.setIdEleitor(votoMongodbEntity.getIdEleitor());
        response.setIdPauta(votoMongodbEntity.getIdPauta());
        response.setAprovacao(votoMongodbEntity.getAprovacao());
        return response;
    }
}
