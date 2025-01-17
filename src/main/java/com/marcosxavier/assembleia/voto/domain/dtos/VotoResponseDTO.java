package com.marcosxavier.assembleia.voto.domain.dtos;

import lombok.Value;

import java.util.UUID;

@Value
public class VotoResponseDTO {
    Long id;
    Long idPauta;
    Long idEleitor;
    Boolean aprovacao;
}
