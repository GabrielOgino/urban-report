package br.com.urbanreport.dto;

import br.com.urbanreport.model.ReportStatus;

public record UpdateStatusRequest(ReportStatus status) {
}
