package com.marcosxavier.assembleia.resultado.service;

import com.marcosxavier.assembleia.eleitor.service.EleitorService;
import com.marcosxavier.assembleia.pauta.entities.Pauta;
import com.marcosxavier.assembleia.pauta.service.PautaService;
import com.marcosxavier.assembleia.pauta.dtos.PautaResponseDTO;
import com.marcosxavier.assembleia.pauta.enums.PautaStatusEnum;
import com.marcosxavier.assembleia.resultado.domain.dtos.ResultadoDto;
import com.marcosxavier.assembleia.resultado.enums.ResultadoPautaEnum;
import com.marcosxavier.assembleia.resultado.enums.ResultadoStatusEnum;
import com.marcosxavier.assembleia.voto.persistence.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ResultadoServiceImpl implements ResultadoService {


    private final VotoRepository votoRepository;
    private final EleitorService eleitorService;
    private final PautaService pautaService;

    @Override
    public ResultadoDto buscaResultadoPorPauta(String idPauta) {
        log.info("[iniciando]ResultadoServiceImpl - buscaResultadoPorPauta: {}", idPauta);
        PautaResponseDTO pauta = pautaService.buscaPorId(idPauta);
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
        log.info("[finalizando]ResultadoServiceImpl - buscaResultadoPorPauta: {}", idPauta);
        return resultadoDto;
    }

    @Override
    public List<ResultadoDto> listaResultados() {
        log.info("[iniciando]ResultadoServiceImpl - listaResultados");
        List<PautaResponseDTO> todasPautas = pautaService.buscaTodasPautas();

        List<Pauta> listaPautas = todasPautas.stream()
                .map(pautaResponseDTO -> new Pauta(
                        pautaResponseDTO.getId(),
                        pautaResponseDTO.getTempoMinutos(),
                        pautaResponseDTO.getAssunto(),
                        pautaResponseDTO.getStatus()
                ))
                .collect(Collectors.toList());

        List<ResultadoDto> listaResultados = new ArrayList<>();
        for (Pauta pauta : listaPautas) {
            listaResultados.add(buscaResultadoPorPauta(pauta.getId()));
        }
        log.info("[finalizando]ResultadoServiceImpl - listaResultados");
        return listaResultados;
    }
}
