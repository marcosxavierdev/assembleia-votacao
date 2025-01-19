package com.marcosxavier.assembleia.resultado.domain.dtos;

import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.resultado.enums.ResultadoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDto {

    private PautaResponseDTO pauta;
    private Long aprovacoes;
    private Long reprovacoes;
    private Long totalVotos;
    private String resultado;
    private ResultadoStatusEnum status;
}
