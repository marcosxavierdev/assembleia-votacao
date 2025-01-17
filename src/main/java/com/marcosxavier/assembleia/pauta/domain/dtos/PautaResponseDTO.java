package com.marcosxavier.assembleia.pauta.domain.dtos;

import com.marcosxavier.assembleia.enums.PautaStatusEnum;
import lombok.Value;
import java.time.LocalDateTime;

@Value
public class PautaResponseDTO {
    Long id;
    LocalDateTime tempoFinal;
    String assunto;
    PautaStatusEnum status;
}
