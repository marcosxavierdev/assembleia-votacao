package com.marcosxavier.assembleia.eleitor.dtos;

import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorRequestDTO {

    @NotEmpty(message = "O campo CPF não pode ser nulo e nem vazio")
    private String cpf;
    private EleitorStatusEnum status;
}
