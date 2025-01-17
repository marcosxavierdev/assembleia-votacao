package com.marcosxavier.assembleia.eleitor.domain.dtos;

import com.marcosxavier.assembleia.eleitor.domain.entities.Eleitor;
import com.marcosxavier.assembleia.enums.EleitorStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorResponseDTO {

    private String id;
    @NotNull (message ="O campo CPF não pode ser nulo")
    private String cpf;
    private EleitorStatusEnum status;

    public EleitorResponseDTO(Eleitor eleitor) {
        this.id = eleitor.getId();
        this.cpf = eleitor.getCpf();
        this.status = eleitor.getStatus();
    }
}
