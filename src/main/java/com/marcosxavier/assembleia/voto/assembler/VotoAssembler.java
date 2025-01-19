package com.marcosxavier.assembleia.voto.assembler;

import com.marcosxavier.assembleia.voto.dtos.VotoResponseDTO;
import com.marcosxavier.assembleia.voto.entities.Voto;

public class VotoAssembler {

    public VotoResponseDTO toResponseDTO(Voto voto){
        VotoResponseDTO response = new VotoResponseDTO();
        response.setId(voto.getId());
        response.setIdEleitor(voto.getIdEleitor());
        response.setIdPauta(voto.getIdPauta());
        response.setAprovacao(voto.getAprovacao());
        return response;
    }
}
