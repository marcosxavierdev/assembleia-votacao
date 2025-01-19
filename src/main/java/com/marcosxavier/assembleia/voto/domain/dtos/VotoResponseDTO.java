package com.marcosxavier.assembleia.voto.domain.dtos;

import com.marcosxavier.assembleia.enums.AprovacaoEnum;
import com.marcosxavier.assembleia.voto.domain.entities.Voto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponseDTO {

    String id;
    String idPauta;
    String idEleitor;
    AprovacaoEnum aprovacao;

    public VotoResponseDTO(Voto voto) {
        this.id = voto.getId();
        this.idPauta = voto.getIdPauta();
        this.idEleitor = voto.getIdEleitor();
        this.aprovacao = voto.getAprovacao();
    }
}
