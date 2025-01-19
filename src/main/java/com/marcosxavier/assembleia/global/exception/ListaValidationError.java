package com.marcosxavier.assembleia.global.exception;

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
