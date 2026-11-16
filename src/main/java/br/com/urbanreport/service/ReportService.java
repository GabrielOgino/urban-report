package br.com.urbanreport.service;

import br.com.urbanreport.dto.CreateReportRequest;
import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public List<Report> findAll() {
        return repository.findAll();
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

    public Optional<Report> findById(Long id){
        return repository.findById(id);
    }

    public Optional<Report> updateStatus(Long id, ReportStatus newStatus){
        Optional<Report> result = repository.findById(id);

        if(result.isPresent()){
            Report report = result.get();
            report.changeStatus(newStatus);
            Report saved = repository.save(report);
            return Optional.of(saved);
        }
        return Optional.empty();
    }


}
