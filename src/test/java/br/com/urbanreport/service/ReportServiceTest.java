package br.com.urbanreport.service;

import br.com.urbanreport.exception.ReportNotFoundException;
import br.com.urbanreport.model.Report;
import br.com.urbanreport.model.ReportCategory;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository repository;

    @InjectMocks
    private ReportService service;

    @Test
    void findByIdShouldReturnReportWhenItExists() {
        Report report = new Report("Buraco", "Teste", ReportCategory.POTHOLE, -23.55, -46.63);
        when(repository.findById(1L)).thenReturn(Optional.of(report));

        Report result = service.findById(1L);

        assertEquals(report, result);
    }

    @Test
    void findByIdShouldThrowWhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void updateStatusShouldChangeAndSave() {
        Report report = new Report("Buraco", "Teste", ReportCategory.POTHOLE, -23.55, -46.63);
        when(repository.findById(1L)).thenReturn(Optional.of(report));

        service.updateStatus(1L, ReportStatus.IN_PROGRESS);

        assertEquals(ReportStatus.IN_PROGRESS, report.getStatus());
        verify(repository).save(report);
    }

    @Test
    void updateStatusShouldNotSaveWhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class,
                () -> service.updateStatus(99L, ReportStatus.IN_PROGRESS));

        verify(repository, never()).save(any());
    }
}