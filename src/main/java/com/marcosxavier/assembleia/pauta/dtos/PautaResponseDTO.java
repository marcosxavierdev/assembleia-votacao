package com.marcosxavier.assembleia.pauta.dtos;

import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.pauta.entities.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaResponseDTO {

    String id;
    String tempoMinutos;
    String assunto;
    PautaStatusEnum status;

    public PautaResponseDTO(Pauta pauta) {
        this.id = pauta.getId();
        this.tempoMinutos = pauta.getTempoMinutos();
        this.assunto = pauta.getAssunto();
        this.status = pauta.getStatus();
    }
}
