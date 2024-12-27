package com.minhvu.energyservice.service;

import com.minhvu.energyservice.model.Energy;
import com.minhvu.energyservice.repository.EnergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EnergyServiceImpl implements EnergyService {
    private final EnergyRepository energyRepository;
    @Override
    public Energy saveEnergy(Energy energy) {
        return energyRepository.save(energy);
    }

    @Override
    public List<Energy> getEnergies() {
        return energyRepository.findAllByOrderByNameDesc();
    }

    @Override
    public Energy getEnergy(String id) {
        return energyRepository.findById(id).orElse(null);
    }

    @Override
    public Energy updateEnergy(String id, Energy energy) {
        Energy energy1 = energyRepository.findById(id).orElse(null);
        assert energy1 != null;
        energy1.setName(energy.getName());
        energy1.setPrice(energy.getPrice());
        energy1.setDescription(energy.getDescription());
        energy1.setImage_url(energy.getImage_url());
        return energyRepository.save(energy1);
    }

    @Override
    public void deleteEnergy(String id) {
        energyRepository.deleteById(id);
    }
}
