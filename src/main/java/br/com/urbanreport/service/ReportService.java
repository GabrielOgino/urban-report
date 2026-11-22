package br.com.urbanreport.service;

import br.com.urbanreport.dto.CreateReportRequest;
import br.com.urbanreport.dto.PageResponse;
import br.com.urbanreport.exception.ReportNotFoundException;
import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public PageResponse<Report> findAll(ReportStatus status, ReportCategory category, Pageable pageable) {
        Page<Report> page;
        if (status != null && category != null) {
            page = repository.findByStatusAndCategory(status, category, pageable);
        } else if (status != null) {
            page = repository.findByStatus(status, pageable);
        } else if (category != null) {
            page = repository.findByCategory(category, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return PageResponse.from(page);
    }

    public Report create(CreateReportRequest request) {
        Report report = new Report(
                request.title(),
                request.description(),
                request.category(),
                request.latitude(),
                request.longitude()
        );
        return repository.save(report);
    }

    public Report findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    public Report updateStatus(Long id, ReportStatus newStatus) {
        Report report = findById(id);
        report.changeStatus(newStatus);
        return repository.save(report);
    }


}
