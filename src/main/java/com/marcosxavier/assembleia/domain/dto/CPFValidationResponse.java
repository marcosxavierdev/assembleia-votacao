package com.marcosxavier.assembleia.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPFValidationResponse {
    private String valid;
    private String formatted;
}
