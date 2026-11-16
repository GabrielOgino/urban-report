package br.com.urbanreport.dto;

import br.com.urbanreport.model.ReportStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(@NotNull ReportStatus status) {
}
