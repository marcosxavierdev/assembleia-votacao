package com.marcosxavier.assembleia.utils.mappers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Pauta;
import com.marcosxavier.assembleia.domain.dto.pauta.PautaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    PautaResponseDTO toPautaResponseDTO(Pauta pauta);
}
