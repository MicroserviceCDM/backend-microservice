package com.minhvu.energyservice.controller;

import com.minhvu.energyservice.dto.model.CreateEnergyRequest;
import com.minhvu.energyservice.model.Energy;
import com.minhvu.energyservice.service.EnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/energy")
public class EnergyController {
    private final EnergyService energyService;

    @GetMapping("/getAllEnergy")
    public List<Energy> getEnergies() {
        return energyService.getEnergies();
    }
    @GetMapping("/getEnergyById/{id}")
    public Energy getEnergy(@PathVariable String id) {
        return energyService.getEnergy(id);
    }

    @PostMapping
    public Energy saveEnergy(@RequestBody CreateEnergyRequest createEnergyRequest) {
        Energy energy = Energy.builder()
                .name(createEnergyRequest.getName())
                .price(createEnergyRequest.getPrice())
                .description(createEnergyRequest.getDescription())
                .image_url(createEnergyRequest.getImage_url())
                .available(createEnergyRequest.getAvailable())
                .build();
        return energyService.saveEnergy(energy);
    }

    @PutMapping("/updateEnergy/{id}")
    public Energy updateEnergy(@PathVariable String id, @RequestBody CreateEnergyRequest createEnergyRequest) {
        Energy energy = Energy.builder()
                .name(createEnergyRequest.getName())
                .price(createEnergyRequest.getPrice())
                .description(createEnergyRequest.getDescription())
                .image_url(createEnergyRequest.getImage_url())
                .available(createEnergyRequest.getAvailable())
                .build();
        return energyService.updateEnergy(id, energy);
    }

    @DeleteMapping("/deleteEnergy/{id}")
    public void deleteEnergy(@PathVariable String id) {
        energyService.deleteEnergy(id);
    }
}
