package br.com.urbanreport.model;
import java.time.LocalDateTime;

public class Report {

    private final Long id;
    private final String title;
    private final String description;
    private final ReportCategory category;
    private ReportStatus status = ReportStatus.OPEN;
    private final double latitude;
    private final double longitude;
    private final LocalDateTime createdAt = LocalDateTime.now();


    public Report(Long id, String title, String description, ReportCategory category, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ReportCategory getCategory() {
        return category;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void changeStatus(ReportStatus newStatus) {
        if (this.status == ReportStatus.RESOLVED || this.status == ReportStatus.REJECTED){
            throw new IllegalStateException("Ocorrência já finalizada");
        }
            this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", createdAt=" + createdAt +
                '}';
    }
}
