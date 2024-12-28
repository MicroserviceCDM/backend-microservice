package io.github.cdm.energyservice.dto.mapper;

import io.github.cdm.energyservice.dto.model.CreateEnergyRequest;
import io.github.cdm.energyservice.model.Energy;
import org.springframework.stereotype.Service;

@Service
public class EnergyMapperImpl implements EnergyMapper{
    @Override
    public Energy toEnergy(CreateEnergyRequest createEnergyRequest) {
        return new Energy();
    }
}
