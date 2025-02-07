package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.application.ports.in.usecases.EleitorUsecase;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.application.ports.in.usecases.ResultadoUsecase;
import com.marcosxavier.assembleia.utils.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDTO;
import com.marcosxavier.assembleia.utils.enums.ResultadoPautaEnum;
import com.marcosxavier.assembleia.utils.enums.ResultadoStatusEnum;
import com.marcosxavier.assembleia.application.ports.out.repositories.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ResultadoService implements ResultadoUsecase {


    private final VotoRepository votoRepository;
    private final EleitorUsecase eleitorUsecase;
    private final PautaUsecase pautaUsecase;

    @Override
    public ResultadoDTO buscaResultadoPorPauta(String idPauta) {
        log.info("[iniciando]ResultadoService - buscaResultadoPorPauta: {}", idPauta);
        Pauta pauta = pautaUsecase.buscaPorId(idPauta);
        Long aprovacoes = votoRepository.contaVotosPorPautaAprovacao(idPauta, "SIM");
        Long reprovacoes = votoRepository.contaVotosPorPautaAprovacao(idPauta, "NAO");

        String resultadoSessao;
        if (aprovacoes > reprovacoes) {
            resultadoSessao = String.valueOf(ResultadoPautaEnum.APROVADO);
        } else if ((aprovacoes < reprovacoes)) {
            resultadoSessao = String.valueOf(ResultadoPautaEnum.REPROVADO);
        } else {
            resultadoSessao = String.valueOf(ResultadoPautaEnum.EMPATE);
        }

        ResultadoStatusEnum status = pauta.getStatus().equals(PautaStatusEnum.OPEN) ?
                ResultadoStatusEnum.IN_PROGRESS : ResultadoStatusEnum.FINISHED;

        ResultadoDTO resultadoDto = new ResultadoDTO();
        resultadoDto.setPauta(pauta);
        resultadoDto.setAprovacoes(aprovacoes);
        resultadoDto.setReprovacoes(reprovacoes);
        resultadoDto.setTotalVotos(aprovacoes+reprovacoes);
        resultadoDto.setResultado(resultadoSessao);
        resultadoDto.setStatus(status);
        log.info("[finalizando]ResultadoService - buscaResultadoPorPauta: {}", idPauta);
        return resultadoDto;
    }

    @Override
    public List<ResultadoDTO> listaResultados() {
        log.info("[iniciando]ResultadoService - listaResultados");
        List<Pauta> todasPautas = pautaUsecase.buscaTodasPautas();

        List<Pauta> listaPautaMongodbEntities = todasPautas.stream()
                .map(pautaResponseDTO -> new Pauta(
                        pautaResponseDTO.getId(),
                        pautaResponseDTO.getTempoMinutos(),
                        pautaResponseDTO.getAssunto(),
                        pautaResponseDTO.getStatus()
                ))
                .collect(Collectors.toList());

        List<ResultadoDTO> listaResultados = new ArrayList<>();
        for (Pauta pauta : listaPautaMongodbEntities) {
            listaResultados.add(buscaResultadoPorPauta(pauta.getId()));
        }
        log.info("[finalizando]ResultadoService - listaResultados");
        return listaResultados;
    }
}
