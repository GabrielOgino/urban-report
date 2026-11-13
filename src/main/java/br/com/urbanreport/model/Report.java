package br.com.urbanreport.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private ReportCategory category;
    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.OPEN;
    private double latitude;
    private double longitude;
    private LocalDateTime createdAt = LocalDateTime.now();


    public Report(String title, String description, ReportCategory category, double latitude, double longitude) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Report() {

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
        if (this.status == ReportStatus.RESOLVED || this.status == ReportStatus.REJECTED) {
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
