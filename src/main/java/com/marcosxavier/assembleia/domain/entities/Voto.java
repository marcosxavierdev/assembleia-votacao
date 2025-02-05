package com.marcosxavier.assembleia.domain.entities;

import com.marcosxavier.assembleia.utils.enums.AprovacaoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class Voto {
    @Schema(name="id", description="id do voto", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @Schema(name="idPauta", description="id da pauta", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String idPauta;

    @Schema(name="idEleitor", description="id do eleitor", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String idEleitor;

    @Schema(name="aprovacao", description="Aprovacao do VotoMongodbEntity", example = "SIM ou NAO")
    private AprovacaoEnum aprovacao;
}
