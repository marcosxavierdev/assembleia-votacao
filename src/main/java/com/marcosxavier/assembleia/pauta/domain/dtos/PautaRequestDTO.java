package com.marcosxavier.assembleia.pauta.domain.dtos;

import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequestDTO {

    String tempoMinutos;
    @NotEmpty(message = "O campo assunto não pode ser nulo e nem vazio")
    String assunto;
    PautaStatusEnum status;
}
