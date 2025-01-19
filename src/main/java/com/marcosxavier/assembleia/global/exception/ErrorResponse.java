package com.marcosxavier.assembleia.global.exception;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Value
@With
@Jacksonized
@Builder
public class ErrorResponse {
    @Builder.Default
    LocalDateTime timestamp = now();
    int status;
    List<ListaValidationError> errors;
    String error;
    String message;
    String path;
}
