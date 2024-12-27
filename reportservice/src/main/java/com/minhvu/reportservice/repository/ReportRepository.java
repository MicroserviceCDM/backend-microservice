package com.minhvu.reportservice.repository;

import com.minhvu.reportservice.model.Report;
import com.minhvu.reportservice.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByType(Enum<Type> type);
}
