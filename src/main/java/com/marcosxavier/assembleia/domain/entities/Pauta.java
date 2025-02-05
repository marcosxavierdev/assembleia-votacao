package com.marcosxavier.assembleia.domain.entities;

import com.marcosxavier.assembleia.utils.enums.PautaStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class Pauta {
    @Schema(name="id", description="id da pauta", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @Schema(name="tempoMinutos", description="tempo em minutos da pauta", example = "8")
    private String tempoMinutos;

    @Schema(name="assunto", description="assunto da pauta", example = "Adesão de novos parceiros")
    private String assunto;

    @Schema(name="status", description="status da pauta", example = "OPEN ou CLOSED")
    private PautaStatusEnum status;
}
