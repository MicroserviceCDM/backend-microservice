package com.minhvu.productservice.service;

import com.minhvu.productservice.dto.CreateEnergyRequest;
import com.minhvu.productservice.dto.UpdateEnergyRequest;
import com.minhvu.productservice.model.Energy;
import com.minhvu.productservice.repository.EnergyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@Service
@AllArgsConstructor
public class EnergyServiceImpl implements EnergyService{
    private final EnergyRepository energyRepository;
    public Page<Energy> findAll(Pageable pageable) {
        return energyRepository.findAll(pageable);
    }

    @Override
    public Energy getProductById(String id) {
        return null;
    }

    @Override
    public Energy createProduct(CreateEnergyRequest createEnergyRequest) {
        Energy energy = Energy.builder()
                .name(createEnergyRequest.getName())
                .price(createEnergyRequest.getPrice())
                .description(createEnergyRequest.getDescription())
                .image_url(createEnergyRequest.getImage_url())
                .build();
        return energyRepository.save(energy);
    }

    @Override
    public Energy updateProduct(UpdateEnergyRequest updateEnergyRequest) {
        Energy energy = energyRepository.findById(updateEnergyRequest.getId()).orElse(null);
        if(energy != null){
            energy.setName(updateEnergyRequest.getName());
            energy.setPrice(updateEnergyRequest.getPrice());
            energy.setDescription(updateEnergyRequest.getDescription());
            energy.setImage_url(updateEnergyRequest.getImage_url());
            return energyRepository.save(energy);
        }
        throw new RuntimeException("Product not found");
    }

    @Override
    public void deleteProduct(String id) {
        energyRepository.deleteById(id);
    }

    @Override
    public List<Energy> findEnergyByNameOrderedByPriceDesc(String name, boolean isAsc) {
        Sort sort = Sort.by("price");
        if (isAsc) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        return energyRepository.findDistinctByNameAllIgnoreCaseOrderByPriceAsc(name, sort);
    }

    @Override
    public List<Energy> findEnergyByNameContains(String name) {
        return energyRepository.findByNameContains(name);
    }


}
