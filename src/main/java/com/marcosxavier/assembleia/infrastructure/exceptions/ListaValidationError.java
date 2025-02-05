package com.marcosxavier.assembleia.infrastructure.exceptions;

import lombok.Data;

@Data
public class ListaValidationError {
    private String field;
    private String message;

    public ListaValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
