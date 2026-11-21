package br.com.urbanreport.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(Long id){
        super("Ocorrência " + id + " não encontrada");
    }
}
