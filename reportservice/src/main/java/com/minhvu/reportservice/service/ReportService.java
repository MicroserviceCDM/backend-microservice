package com.minhvu.reportservice.service;

import com.minhvu.reportservice.dto.CreateReportRequest;
import com.minhvu.reportservice.dto.UpdateReportRequest;
import com.minhvu.reportservice.model.Report;
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