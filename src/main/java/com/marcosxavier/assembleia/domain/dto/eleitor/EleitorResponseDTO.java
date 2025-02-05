package com.marcosxavier.assembleia.domain.dto.eleitor;

import com.marcosxavier.assembleia.adapters.outbound.databaseentities.EleitorMongodbEntity;
import com.marcosxavier.assembleia.utils.enums.EleitorStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorResponseDTO {

    @Schema(name="id", description="id do eleitor", example = "8f5f19f5-8fcb-450a-acb9-8719aec42058")
    private String id;
    @Schema(name="cpf", description="cpf do eleitor", example = "11122233344")
    private String cpf;
    @Schema(name="status", description="status da situação do eleitor", example = "ABLE_TO_VOTE ou UNABLE_TO_VOTE")
    private EleitorStatusEnum status;

    public EleitorResponseDTO(EleitorMongodbEntity eleitorMongodbEntity) {
        this.id = eleitorMongodbEntity.getId();
        this.cpf = eleitorMongodbEntity.getCpf();
        this.status = eleitorMongodbEntity.getStatus();
    }
}
