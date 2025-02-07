package com.marcosxavier.assembleia.utils.mappers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.Eleitor;
import com.marcosxavier.assembleia.domain.dto.eleitor.EleitorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EleitorMapper {

    EleitorMapper INSTANCE = Mappers.getMapper(EleitorMapper.class);

    EleitorResponseDTO toEleitorResponseDTO(Eleitor eleitor);
}
