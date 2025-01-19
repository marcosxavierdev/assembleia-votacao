package com.marcosxavier.assembleia.eleitor.assembler;

import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.entities.Eleitor;

public class EleitorAssembler {

    public  EleitorResponseDTO toResponseDTO(Eleitor eleitor){
        EleitorResponseDTO response = new EleitorResponseDTO();
        response.setId(eleitor.getId());
        response.setCpf(eleitor.getCpf());
        response.setStatus(eleitor.getStatus());
        return response;
    }
}
