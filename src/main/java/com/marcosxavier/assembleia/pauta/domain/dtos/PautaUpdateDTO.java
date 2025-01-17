package com.marcosxavier.assembleia.pauta.domain.dtos;

import com.marcosxavier.assembleia.enums.PautaStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class PautaUpdateDTO {
    @NotNull
    Long id;
    Long minutos;
    @NotNull
    String assunto;
    PautaStatusEnum status;
}
