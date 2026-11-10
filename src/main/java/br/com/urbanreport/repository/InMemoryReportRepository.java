package br.com.urbanreport.repository;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryReportRepository implements ReportRepository {

    private final List<Report> reports = new ArrayList<>();

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

}

