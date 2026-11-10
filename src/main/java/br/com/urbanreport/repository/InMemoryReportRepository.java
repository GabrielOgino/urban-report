package br.com.urbanreport.repository;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryReportRepository implements ReportRepository {

    private final List<Report> reports = new ArrayList<>();

    private long currentId = 0;


    public InMemoryReportRepository() {
        save(new Report(nextId(), "Buraco na via", "Buraco grande na esquina",
                ReportCategory.POTHOLE, -23.5505, -46.6333));
        save(new Report(nextId(), "Poste apagado", "Rua escura há uma semana",
                ReportCategory.STREET_LIGHTING, -23.5510, -46.6340));
    }

    @Override
    public Report save(Report report) {
        reports.add(report);
        return report;
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reports.stream()
                .filter(report -> report.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Report> findAll() {
        return new ArrayList<>(reports);
    }

    @Override
    public List<Report> findByStatus(ReportStatus status) {
        return reports.stream()
                .filter(report -> report.getStatus() == status)
                .toList();
    }

    @Override
    public List<Report> findByCategory(ReportCategory category) {
        return reports.stream()
                .filter(report -> report.getCategory() == category)
                .toList();
    }

    @Override
    public Long nextId() {
        currentId++;
        return currentId;
    }

}

