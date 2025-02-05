package com.marcosxavier.assembleia.domain.entities;

import com.marcosxavier.assembleia.utils.enums.EleitorStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class Eleitor {
    @Schema(name="id", description="id do eleitor", required=true, example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @Schema(name="cpf", description="cpf do eleitor", required=true, example = "11122233344")
    private String cpf;

    @Schema(name="status", description="status da situação do eleitor", example = "ABLE_TO_VOTE ou UNABLE_TO_VOTE")
    private EleitorStatusEnum status;
}
