package br.com.urbanreport;

import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;

public class Playground {
    public static void main(String[] args) {

        Report report = new Report(213L, "Buraco na via","Caí em um buraco que furou meu pneu",
                ReportCategory.POTHOLE, -23.5505, -46.6333);

        System.out.println(report);

        report.changeStatus(ReportStatus.UNDER_REVIEW);
        System.out.println(report.getStatus());

        report.changeStatus(ReportStatus.RESOLVED);
        System.out.println(report.getStatus());

        try{
            report.changeStatus(ReportStatus.OPEN);
        } catch (IllegalStateException e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
