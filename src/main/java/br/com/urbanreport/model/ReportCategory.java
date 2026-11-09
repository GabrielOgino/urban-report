package br.com.urbanreport.model;

public enum ReportCategory {
    POTHOLE("Buraco na via"),
    STREET_LIGHTING("Iluminação pública"),
    ILLEGAL_DUMPING("Descarte irregular de lixo"),
    SIDEWALK("Calçada danificada"),
    SIGNAGE("Sinalização"),
    OTHER("Outros");

    private final String description;

    ReportCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
