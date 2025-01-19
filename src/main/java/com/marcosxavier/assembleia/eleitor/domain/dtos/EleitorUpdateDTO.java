package com.marcosxavier.assembleia.eleitor.domain.dtos;

import com.marcosxavier.assembleia.enums.EleitorStatusEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorUpdateDTO {

    @NotEmpty(message = "O campo id não pode ser nulo e nem vazio")
    private String id;
    @NotEmpty(message = "O campo CPF não pode ser nulo e nem vazio")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres")
    private String cpf;
    @NotEmpty(message = "O campo status não pode ser nulo e nem vazio")
    private EleitorStatusEnum status;
}
