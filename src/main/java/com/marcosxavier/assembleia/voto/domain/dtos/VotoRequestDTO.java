package com.marcosxavier.assembleia.voto.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class VotoRequestDTO {
    @NotNull
    Long idPauta;
    @NotNull
    Long idEleitor;
    @NotNull
    Boolean aprovacao;
}
