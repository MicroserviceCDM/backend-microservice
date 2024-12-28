package io.github.cdm.energyservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "energy")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Energy {
        @Id
        private String id;
        private String name;
        private String description;
        private String image_url;
        private String price;
        private Boolean available;
}
