package br.com.urbanreport.repository;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Page<Report> findByStatus(ReportStatus status, Pageable pageable);

    Page<Report> findByCategory(ReportCategory category, Pageable pageable);

    Page<Report> findByStatusAndCategory(ReportStatus status, ReportCategory category, Pageable pageable);
}
