package com.marcosxavier.assembleia.voto.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class VotoUpdateDTO {
    @NotNull
    Long id;
    @NotNull
    Long idPauta;
    @NotNull
    Long idEleitor;
    @NotNull
    Boolean aprovacao;
}
