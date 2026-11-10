package br.com.urbanreport.controller;

import br.com.urbanreport.dto.CreateReportRequest;
import br.com.urbanreport.dto.UpdateStatusRequest;
import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.repository.ReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {

    private final ReportRepository repository;

    public ReportController(ReportRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reports")
    public List<Report> findAll() {
        return repository.findAll();
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<Report> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reports")
    public ResponseEntity<Report> create(@RequestBody CreateReportRequest request) {
        Report report = new Report(repository.nextId(),
                request.title(),
                request.description(),
                request.category(),
                request.latitude(),
                request.longitude()
        );

        Report saved = repository.save(report);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/reports/{id}/status")
    public ResponseEntity<Report> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {

        Optional<Report> result = repository.findById(id);

        if (result.isPresent()) {
            Report report = result.get();
            try{
                report.changeStatus(request.status());

                return ResponseEntity.ok(report);
            }
            catch(IllegalStateException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
