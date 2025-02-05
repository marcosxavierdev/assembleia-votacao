package com.marcosxavier.assembleia.domain.dto.eleitor;

import com.marcosxavier.assembleia.utils.enums.EleitorStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorRequestDTO {

    @NotEmpty(message = "O campo CPF não pode ser nulo e nem vazio")
    @Schema(name="cpf", description="cpf do eleitor", required=true, example = "11122233344")
    private String cpf;

    @Schema(name="status", description="status da situação do eleitor", example = "ABLE_TO_VOTE ou UNABLE_TO_VOTE")
    private EleitorStatusEnum status;
}
