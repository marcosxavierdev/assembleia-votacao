package com.marcosxavier.assembleia.domain.dto.voto;

import com.marcosxavier.assembleia.utils.enums.AprovacaoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoUpdateDTO {

    @NotEmpty(message = "O campo id não pode ser nulo e nem vazio")
    @Schema(name="id", description="id do voto", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;

    @NotEmpty(message = "O campo idPauta não pode ser nulo e nem vazio")
    @Schema(name="idPauta", description="id da pauta", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String idPauta;

    @NotEmpty(message = "O campo idEleitor não pode ser nulo e nem vazio")
    @Schema(name="idEleitor", description="id do eleitor", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String idEleitor;

    @NotNull
    @Schema(name="aprovacao", description="Aprovacao do Voto", example = "SIM ou NAO")
    private AprovacaoEnum aprovacao;
}
