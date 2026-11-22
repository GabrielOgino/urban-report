package br.com.urbanreport.model;
import br.com.urbanreport.exception.InvalidStatusTransitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void newReportShouldStartAsOpen() {
        // Arrange + Act
        Report report = new Report("Buraco", "Teste", ReportCategory.POTHOLE, -23.55, -46.63);

        // Assert
        assertEquals(ReportStatus.OPEN, report.getStatus());
    }

    @Test
    void changeStatusShouldUpdateStatus(){

        Report report = new Report("Luz queimada", "Teste", ReportCategory.STREET_LIGHTING, -24.77, -87.90);
        report.changeStatus(ReportStatus.IN_PROGRESS);

        assertEquals(ReportStatus.IN_PROGRESS, report.getStatus());
    }

    @Test
    void shouldNotChangeStatusWhenResolved() {
        Report report = new Report("Calçada rachada", "Teste", ReportCategory.SIDEWALK, -34.82, -91.37);
        report.changeStatus(ReportStatus.RESOLVED);

        assertThrows(InvalidStatusTransitionException.class,
                () -> report.changeStatus(ReportStatus.OPEN));
    }

    @Test
    void shouldNotChangeStatusWhenRejected() {
        Report report = new Report("Placa caida", "Teste", ReportCategory.SIGNAGE, -34.82, -91.37);
        report.changeStatus(ReportStatus.REJECTED);

        assertThrows(InvalidStatusTransitionException.class,
                () -> report.changeStatus(ReportStatus.OPEN));
    }


    }