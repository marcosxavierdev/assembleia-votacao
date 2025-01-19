package com.marcosxavier.assembleia.eleitor.domain.dtos;

import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import com.marcosxavier.assembleia.eleitor.enums.EleitorStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorResponseDTO {

    private String id;
    private String cpf;
    private EleitorStatusEnum status;

    public EleitorResponseDTO(Eleitor eleitor) {
        this.id = eleitor.getId();
        this.cpf = eleitor.getCpf();
        this.status = eleitor.getStatus();
    }
}
