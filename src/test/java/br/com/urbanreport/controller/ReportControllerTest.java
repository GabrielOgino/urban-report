package br.com.urbanreport.controller;

import br.com.urbanreport.exception.InvalidStatusTransitionException;
import br.com.urbanreport.exception.ReportNotFoundException;
import br.com.urbanreport.model.ReportStatus;
import br.com.urbanreport.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReportService service;

    @Test
    void shouldReturn404WhenReportNotFound() throws Exception {
        when(service.findById(99L)).thenThrow(new ReportNotFoundException(99L));

        mockMvc.perform(get("/reports/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Ocorrência 99 não econtrada"));
    }

    @Test
    void shouldReturn400WhenRequestIsInvalid() throws Exception {
        String invalidJason = """
                { "title": "", "description": "Teste", "latitude": 500, "longitude": -46.63 } """;

        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJason))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.length").value(3));
    }

    @Test
    void shouldReturn409WhenReportIsFinalized() throws Exception {
        when(service.updateStatus(1L, ReportStatus.OPEN))
                .thenThrow(new InvalidStatusTransitionException("Ocorrência já finalizada"));

        String statusBody = """
            { "status": "OPEN" }
            """;

        mockMvc.perform(patch("/reports/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(statusBody))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Ocorrência já finalizada"));
    }
}