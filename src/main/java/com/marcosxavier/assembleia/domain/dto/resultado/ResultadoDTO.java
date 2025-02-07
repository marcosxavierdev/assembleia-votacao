package com.marcosxavier.assembleia.domain.dto.resultado;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.utils.enums.ResultadoStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO extends RepresentationModel<ResultadoDTO> {

    @Schema(name="pauta", description="informações da pauta")
    private Pauta pauta;

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

    public ResultadoDTO(ResultadoDTO resultado) {
        this.pauta = resultado.getPauta();
        this.aprovacoes = resultado.getAprovacoes();
        this.reprovacoes = resultado.getReprovacoes();
        this.totalVotos = resultado.getTotalVotos();
        this.resultado = resultado.getResultado();
        this.status = resultado.getStatus();
    }
}
