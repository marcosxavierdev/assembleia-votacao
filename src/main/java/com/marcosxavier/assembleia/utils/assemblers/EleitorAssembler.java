package com.marcosxavier.assembleia.utils.assemblers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.EleitorMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;

public class EleitorAssembler {

    public  EleitorResponseDTO toResponseDTO(EleitorMongodbEntity eleitorMongodbEntity){
        EleitorResponseDTO response = new EleitorResponseDTO();
        response.setId(eleitorMongodbEntity.getId());
        response.setCpf(eleitorMongodbEntity.getCpf());
        response.setStatus(eleitorMongodbEntity.getStatus());
        return response;
    }
}
