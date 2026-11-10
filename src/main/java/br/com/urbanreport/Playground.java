package br.com.urbanreport;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.repository.InMemoryReportRepository;
import br.com.urbanreport.repository.ReportRepository;

public class Playground {
    public static void main(String[] args) {

        ReportRepository repository = new InMemoryReportRepository();

        Report r1 = new Report(1L, "Buraco na via", "Buraco grande na esquina",
                ReportCategory.POTHOLE, -23.5505, -46.6333);
        Report r2 = new Report(2L, "Poste apagado", "Rua escura há uma semana",
                ReportCategory.STREET_LIGHTING, -23.5510, -46.6340);
        Report r3 = new Report(3L, "Lixo na calçada", "Entulho descartado na calçada",
                ReportCategory.ILLEGAL_DUMPING, -23.5520, -46.6350);

        repository.save(r1);
        repository.save(r2);
        repository.save(r3);

        System.out.println("Total: " + repository.findAll().size());
        System.out.println("Busca id 2: " + repository.findById(2L).isPresent());
        System.out.println("Busca id 99: " + repository.findById(99L).isPresent());

        r2.changeStatus(ReportStatus.IN_PROGRESS);
        System.out.println("Abertos: " + repository.findByStatus(ReportStatus.OPEN).size());
    }
}
