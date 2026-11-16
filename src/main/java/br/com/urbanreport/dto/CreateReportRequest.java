package br.com.urbanreport.dto;

import br.com.urbanreport.model.ReportCategory;
import jakarta.validation.constraints.*;


public record CreateReportRequest(
        @NotBlank @Size(max = 255) String title,
        @Size(max = 255) String description,
        @NotNull ReportCategory category,
        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") Double latitude,
        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") Double longitude
) {}
