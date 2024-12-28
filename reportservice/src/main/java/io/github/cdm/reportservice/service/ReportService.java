package io.github.cdm.reportservice.service;

import io.github.cdm.reportservice.dto.CreateReportRequest;
import io.github.cdm.reportservice.dto.UpdateReportRequest;
import io.github.cdm.reportservice.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportService {
    Report createReport(CreateReportRequest createReportRequest);
    Page<Report> getAllReports(Pageable pageable);
    Report getReportById(String id);
    Report updateReport(UpdateReportRequest report);
    void deleteReport(String id);
    List<Report> findByType(String type);
}