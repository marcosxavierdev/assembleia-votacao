package com.marcosxavier.assembleia.pauta.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PautaRequestDTO {
    Long tempo;
    @NotNull
    String assunto;
}
