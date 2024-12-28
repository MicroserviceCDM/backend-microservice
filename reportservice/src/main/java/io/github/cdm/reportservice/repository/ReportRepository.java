package io.github.cdm.reportservice.repository;

import io.github.cdm.reportservice.model.Report;
import io.github.cdm.reportservice.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByType(Enum<Type> type);
}
