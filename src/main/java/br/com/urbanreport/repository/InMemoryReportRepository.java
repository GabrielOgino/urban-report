package br.com.urbanreport.repository;

import br.com.urbanreport.model.Report;
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
        for(Report report : reports){
            if (report.getId().equals(id)){
                return Optional.of(report);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Report> findAll() {
        return new ArrayList<>(reports);
    }

    @Override
    public List<Report> findByStatus(ReportStatus status) {
        List<Report> results = new ArrayList<>();
        for(Report report : reports) {
            if(report.getStatus() == status){
                results.add(report);
            }
        }
        return results;
    }
}

