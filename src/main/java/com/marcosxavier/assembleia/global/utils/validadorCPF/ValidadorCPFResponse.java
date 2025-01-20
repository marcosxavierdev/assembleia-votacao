package com.marcosxavier.assembleia.global.utils.validadorCPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidadorCPFResponse {
    private String valid;
    private String formatted;
}
