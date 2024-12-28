package io.github.cdm.reportservice;

import io.github.cdm.reportservice.dto.CreateReportRequest;
import io.github.cdm.reportservice.dto.UpdateReportRequest;
import io.github.cdm.reportservice.model.Report;
import io.github.cdm.reportservice.model.Type;
import io.github.cdm.reportservice.repository.ReportRepository;
import io.github.cdm.reportservice.service.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllReportsReturnsPageOfReports() {
        Pageable pageable = PageRequest.of(0, 10);
        when(reportRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<Report> result = reportService.getAllReports(pageable);

        assertNotNull(result);
    }

    @Test
    public void getReportByIdReturnsNullWhenNotFound() {
        when(reportRepository.findById(any())).thenReturn(Optional.empty());

        Report result = reportService.getReportById("1");

        assertEquals(null, result);
    }

    @Test
    public void createReportReturnsCreatedReport() {
        CreateReportRequest request = new CreateReportRequest();
        request.setType(Type.USER.toString());
        Report report = new Report();
        when(reportRepository.save(any())).thenReturn(report);

        Report result = reportService.createReport(request);

        assertEquals(report, result);
    }

    @Test
    public void updateReportReturnsUpdatedReport() {
        UpdateReportRequest request = new UpdateReportRequest();
        Report report = new Report();
        when(reportRepository.findById(any())).thenReturn(Optional.of(report));
        when(reportRepository.save(any())).thenReturn(report);

        Report result = reportService.updateReport(request);

        assertEquals(report, result);
    }

    @Test
    public void findByTypeReturnsListOfReports() {
        when(reportRepository.findByType(any())).thenReturn(Collections.singletonList(new Report()));

        var result = reportService.findByType("USER");

        assertEquals(1, result.size());
    }
}