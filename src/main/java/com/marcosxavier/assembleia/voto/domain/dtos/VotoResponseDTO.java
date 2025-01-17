package com.marcosxavier.assembleia.voto.domain.dtos;

import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponseDTO {
    String id;
    String idPauta;
    String idEleitor;
    Boolean aprovacao;

    public VotoResponseDTO(Voto voto) {
        this.id = voto.getId();
        this.idPauta = voto.getIdPauta();
        this.idEleitor = voto.getIdEleitor();
        this.aprovacao = voto.getAprovacao();
    }
}
