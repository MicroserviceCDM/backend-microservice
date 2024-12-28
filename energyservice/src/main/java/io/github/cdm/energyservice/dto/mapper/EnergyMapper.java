package io.github.cdm.energyservice.dto.mapper;


import io.github.cdm.energyservice.dto.model.CreateEnergyRequest;
import io.github.cdm.energyservice.model.Energy;

public interface EnergyMapper {
    Energy toEnergy(CreateEnergyRequest createEnergyRequest);
}
