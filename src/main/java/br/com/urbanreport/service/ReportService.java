package br.com.urbanreport.service;

import br.com.urbanreport.dto.CreateReportRequest;
import br.com.urbanreport.dto.PageResponse;
import br.com.urbanreport.exception.ReportNotFoundException;
import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.repository.ReportRepository;
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

    public PageResponse<Report> findAll(Pageable pageable) {
        return PageResponse.from(repository.findAll(pageable));
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

    public Report findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    public Report updateStatus(Long id, ReportStatus newStatus){
        Report report = findById(id);
        report.changeStatus(newStatus);
        return repository.save(report);
    }


}
