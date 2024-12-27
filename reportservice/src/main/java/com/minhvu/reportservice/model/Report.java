package com.minhvu.reportservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reports")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Report {
        @Id
        private String id;
        private String userId;
        private String image;
        private String title;
        private String description;
        @Enumerated(EnumType.STRING)
        private Status status;
        @Enumerated(EnumType.STRING)
        private Type type;
        private String createdDate;
        private String updatedDate;
}
