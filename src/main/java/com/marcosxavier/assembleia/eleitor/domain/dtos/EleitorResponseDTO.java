package com.marcosxavier.assembleia.eleitor.domain.dtos;

import com.marcosxavier.assembleia.enums.EleitorStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EleitorResponseDTO {

    private Long id;
    @NotNull
    private String cpf;
    private EleitorStatusEnum status;

}
