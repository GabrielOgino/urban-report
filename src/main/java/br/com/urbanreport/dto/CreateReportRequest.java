package br.com.urbanreport.dto;

import br.com.urbanreport.model.ReportCategory;

public record CreateReportRequest(
        String title,
        String description,
        ReportCategory category,
        double latitude,
        double longitude
) {
}
