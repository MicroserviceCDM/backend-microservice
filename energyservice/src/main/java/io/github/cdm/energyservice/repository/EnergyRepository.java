package io.github.cdm.energyservice.repository;

import io.github.cdm.energyservice.model.Energy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnergyRepository extends MongoRepository<Energy, String> {
    List<Energy> findAllByNameContaining(String text);
    List<Energy> findAllByOrderByNameDesc();
}
