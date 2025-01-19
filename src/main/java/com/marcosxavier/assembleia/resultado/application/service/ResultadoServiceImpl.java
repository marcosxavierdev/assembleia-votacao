package com.marcosxavier.assembleia.resultado.application.service;

import com.marcosxavier.assembleia.eleitor.application.service.EleitorService;
import com.marcosxavier.assembleia.pauta.application.service.PautaService;
import com.marcosxavier.assembleia.pauta.domain.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import com.marcosxavier.assembleia.resultado.enums.ResultadoStatusEnum;
import com.marcosxavier.assembleia.voto.persistence.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ResultadoServiceImpl implements ResultadoService {


    private final VotoRepository votoRepository;
    private final EleitorService eleitorService;
    private final PautaService pautaService;

    @Override
    public ResultadoDto buscaResultadoPorPauta(String idPauta) {
        PautaResponseDTO pauta = pautaService.buscaPorId(idPauta);
        Long aprovacoes = votoRepository.contaVotosPorPautaAprovacao(idPauta, "SIM");
        Long reprovacoes = votoRepository.contaVotosPorPautaAprovacao(idPauta, "NAO");

        String resultadoSessao;
        if (aprovacoes > reprovacoes) {
            resultadoSessao = "Aprovado";
        } else if ((aprovacoes < reprovacoes)) {
            resultadoSessao = "Reprovado";
        } else {
            resultadoSessao = "Empate";
        }

        ResultadoStatusEnum status = pauta.getStatus().equals(PautaStatusEnum.OPEN) ?
                ResultadoStatusEnum.IN_PROGRESS : ResultadoStatusEnum.FINISHED;

        ResultadoDto resultadoDto = new ResultadoDto();
        resultadoDto.setPauta(pauta);
        resultadoDto.setAprovacoes(aprovacoes);
        resultadoDto.setReprovacoes(reprovacoes);
        resultadoDto.setTotalVotos(aprovacoes+reprovacoes);
        resultadoDto.setResultado(resultadoSessao);
        resultadoDto.setStatus(status);

        return resultadoDto;

    }
}
