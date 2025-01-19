package com.marcosxavier.assembleia.eleitor.assembler;

import com.marcosxavier.assembleia.eleitor.dtos.EleitorResponseDTO;
import com.marcosxavier.assembleia.eleitor.entities.Eleitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EleitorMapper {

    EleitorMapper INSTANCE = Mappers.getMapper(EleitorMapper.class);

    EleitorResponseDTO toEleitorResponseDTO(Eleitor eleitor);
}
