package br.com.urbanreport.controller;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.repository.ReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
