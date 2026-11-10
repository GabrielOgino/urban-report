package br.com.urbanreport.repository;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {

    Report save(Report report);

    Optional<Report> findById(Long id);

    List<Report> findAll();

    List<Report> findByStatus(ReportStatus status);

    List<Report> findByCategory(ReportCategory category);

    Long nextId();

}
