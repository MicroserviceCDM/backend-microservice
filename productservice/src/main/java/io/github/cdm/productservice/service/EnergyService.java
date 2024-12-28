package io.github.cdm.productservice.service;

import io.github.cdm.productservice.dto.CreateEnergyRequest;
import io.github.cdm.productservice.dto.UpdateEnergyRequest;
import io.github.cdm.productservice.model.Energy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnergyService {
    Page<Energy> findAll(Pageable pageable);
    Energy getProductById(String id);

    Energy createProduct(CreateEnergyRequest createEnergyRequest);

    Energy updateProduct(UpdateEnergyRequest updateEnergyRequest);

    void deleteProduct(String id);
    List<Energy> findEnergyByNameOrderedByPriceDesc(String name, boolean isAsc);

    List<Energy> findEnergyByNameContains(String name);
}
