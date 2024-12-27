package com.minhvu.energyservice.dto.mapper;


import com.minhvu.energyservice.dto.model.CreateEnergyRequest;
import com.minhvu.energyservice.model.Energy;

public interface EnergyMapper {
    Energy toEnergy(CreateEnergyRequest createEnergyRequest);
}
