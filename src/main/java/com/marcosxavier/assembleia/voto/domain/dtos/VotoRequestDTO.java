package com.marcosxavier.assembleia.voto.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class VotoRequestDTO {
    @NotEmpty(message = "O campo idPauta não pode ser nulo e nem vazio")
    String idPauta;
    @NotEmpty(message = "O campo idEleitor não pode ser nulo e nem vazio")
    String idEleitor;
    @NotNull
    Boolean aprovacao;
}
