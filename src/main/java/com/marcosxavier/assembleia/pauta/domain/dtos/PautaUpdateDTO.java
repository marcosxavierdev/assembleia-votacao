package com.marcosxavier.assembleia.pauta.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaUpdateDTO {

    @NotEmpty(message = "O id CPF não pode ser nulo e nem vazio")
    String id;
    String tempoMinutos;
    @NotEmpty(message = "O campo assunto não pode ser nulo e nem vazio")
    String assunto;
}
