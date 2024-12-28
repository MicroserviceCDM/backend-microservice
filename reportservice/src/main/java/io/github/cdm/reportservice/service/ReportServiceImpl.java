package io.github.cdm.reportservice.service;

import io.github.cdm.reportservice.dto.CreateReportRequest;
import io.github.cdm.reportservice.dto.UpdateReportRequest;
import io.github.cdm.reportservice.model.Report;
import io.github.cdm.reportservice.model.Status;
import io.github.cdm.reportservice.model.Type;
import io.github.cdm.reportservice.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;

    @Override
    public Report createReport(CreateReportRequest createReportRequest) {
        Report report = Report.builder()
                .userId(createReportRequest.getUserId())
                .title(createReportRequest.getTitle())
                .description(createReportRequest.getDescription())
                .image(createReportRequest.getImage())
                .status(Status.PENDING)
                .type(Type.valueOf(createReportRequest.getType()))
                .createdDate(LocalDateTime.now().toString())
                .build();
        return reportRepository.save(report);
    }

    public Page<Report> getAllReports(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public Report getReportById(String id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public Report updateReport(UpdateReportRequest report) {
        Report reportToUpdate = reportRepository.findById(report.getId()).orElse(null);
        if (reportToUpdate == null) {
            throw new RuntimeException("Report not found");
        }
        if (report.getStatus() != null) {
            switch (report.getStatus()) {
                case "APPROVED" -> reportToUpdate.setStatus(Status.APPROVED);
                case "REJECTED" -> reportToUpdate.setStatus(Status.REJECTED);
                case "PENDING" -> reportToUpdate.setStatus(Status.PENDING);
                default -> throw new RuntimeException("Invalid status");
            }
        }
        return reportRepository.save(reportToUpdate);
    }

    @Override
    public void deleteReport(String id) {
        reportRepository.deleteById(id);
    }

    @Override
    public List<Report> findByType(String type) {
        if (Objects.equals(type, "STAFF"))
        {
            return reportRepository.findByType(Type.STAFF);
        }
        return reportRepository.findByType(Type.USER);
    }
}