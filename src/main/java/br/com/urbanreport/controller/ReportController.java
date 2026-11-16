package br.com.urbanreport.controller;

import br.com.urbanreport.dto.CreateReportRequest;
import br.com.urbanreport.dto.UpdateStatusRequest;
import br.com.urbanreport.model.Report;
import br.com.urbanreport.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public List<Report> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Report> create(@Valid @RequestBody CreateReportRequest request) {

        Report saved = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Report> updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest request) {
        try {
            return service.updateStatus(id, request.status())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
