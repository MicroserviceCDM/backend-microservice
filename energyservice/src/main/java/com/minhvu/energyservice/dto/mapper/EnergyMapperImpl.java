package com.minhvu.energyservice.dto.mapper;

import com.minhvu.energyservice.dto.model.CreateEnergyRequest;
import com.minhvu.energyservice.model.Energy;
import org.springframework.stereotype.Service;

@Service
public class EnergyMapperImpl implements EnergyMapper{
    @Override
    public Energy toEnergy(CreateEnergyRequest createEnergyRequest) {
        return new Energy();
    }
}
