package com.marcosxavier.assembleia.resultado.domain.dtos;

import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.resultado.enums.ResultadoStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDto {

    @Schema(name="pauta", description="informações da pauta")
    private PautaResponseDTO pauta;

    @Schema(name="aprovacoes", description="total de aprovações da pauta", example = "4")
    private Long aprovacoes;

    @Schema(name="reprovacoes", description="total de reprovações da pauta", example = "3")
    private Long reprovacoes;

    @Schema(name="totalVotos", description="total de votos da pauta", example = "7")
    private Long totalVotos;

    @Schema(name="resultado", description="resultado da pauta", example = "APROVADO, REPROVADO ou EMPATE")
    private String resultado;

    @Schema(name="status", description="status do resultado", example = "IN_PROGRESS ou FINISHED")
    private ResultadoStatusEnum status;
}
