package com.marcosxavier.assembleia.application.services;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.PautaMongodbEntity;
import com.marcosxavier.assembleia.application.ports.in.usecases.EleitorUsecase;
import com.marcosxavier.assembleia.application.ports.in.usecases.PautaUsecase;
import com.marcosxavier.assembleia.application.ports.in.usecases.ResultadoUsecase;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import com.marcosxavier.assembleia.utils.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.domain.dto.resultado.ResultadoDto;
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
    public ResultadoDto buscaResultadoPorPauta(String idPauta) {
        log.info("[iniciando]ResultadoService - buscaResultadoPorPauta: {}", idPauta);
        PautaResponseDTO pauta = pautaUsecase.buscaPorId(idPauta);
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

        ResultadoDto resultadoDto = new ResultadoDto();
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
    public List<ResultadoDto> listaResultados() {
        log.info("[iniciando]ResultadoService - listaResultados");
        List<PautaResponseDTO> todasPautas = pautaUsecase.buscaTodasPautas();

        List<PautaMongodbEntity> listaPautaMongodbEntities = todasPautas.stream()
                .map(pautaResponseDTO -> new PautaMongodbEntity(
                        pautaResponseDTO.getId(),
                        pautaResponseDTO.getTempoMinutos(),
                        pautaResponseDTO.getAssunto(),
                        pautaResponseDTO.getStatus()
                ))
                .collect(Collectors.toList());

        List<ResultadoDto> listaResultados = new ArrayList<>();
        for (PautaMongodbEntity pautaMongodbEntity : listaPautaMongodbEntities) {
            listaResultados.add(buscaResultadoPorPauta(pautaMongodbEntity.getId()));
        }
        log.info("[finalizando]ResultadoService - listaResultados");
        return listaResultados;
    }
}
