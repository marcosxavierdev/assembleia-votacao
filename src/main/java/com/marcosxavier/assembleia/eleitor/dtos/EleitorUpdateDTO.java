package com.marcosxavier.assembleia.eleitor.dtos;

import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorUpdateDTO{

    @NotEmpty(message = "O campo id não pode ser nulo e nem vazio")
    @Schema(name="id", description="id do eleitor", required=true, example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;
    @NotEmpty(message = "O campo CPF não pode ser nulo e nem vazio")
    @Schema(name="cpf", description="cpf do eleitor", required=true, example = "11122233344")
    private String cpf;
    @Schema(name="status", description="status da situação do eleitor", example = "ABLE_TO_VOTE ou UNABLE_TO_VOTE")
    private EleitorStatusEnum status;
}
