package br.com.urbanreport.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(int status, String message, LocalDateTime timestamp, List<FieldErrorResponse> errors) {
}
