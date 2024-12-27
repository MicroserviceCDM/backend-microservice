package com.minhvu.productservice.service;

import com.minhvu.productservice.dto.CreateEnergyRequest;
import com.minhvu.productservice.dto.UpdateEnergyRequest;
import com.minhvu.productservice.model.Energy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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
