package br.com.urbanreport.controller;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.repository.ReportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
