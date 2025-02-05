package com.marcosxavier.assembleia.utils.mappers;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.VotoMongodbEntity;
import com.marcosxavier.assembleia.domain.dto.voto.VotoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotoMapper {

    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    VotoResponseDTO toVotoResponseDTO(VotoMongodbEntity votoMongodbEntity);
}
