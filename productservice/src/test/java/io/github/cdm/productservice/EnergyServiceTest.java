package io.github.cdm.productservice;

import io.github.cdm.productservice.dto.CreateEnergyRequest;
import io.github.cdm.productservice.dto.UpdateEnergyRequest;
import io.github.cdm.productservice.model.Energy;
import io.github.cdm.productservice.repository.EnergyRepository;
import io.github.cdm.productservice.service.EnergyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EnergyServiceTest {

    @InjectMocks
    private EnergyServiceImpl energyService;

    @Mock
    private EnergyRepository energyRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllReturnsPageOfProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        when(energyRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<Energy> result = energyService.findAll(pageable);

        assertNotNull(result);
    }

    @Test
    public void getProductByIdReturnsNullWhenNotFound() {
        when(energyRepository.findById(any())).thenReturn(Optional.empty());

        Energy result = energyService.getProductById("1");

        assertEquals(null, result);
    }

    @Test
    public void createProductReturnsCreatedProduct() {
        CreateEnergyRequest request = new CreateEnergyRequest();
        Energy energy = new Energy();
        when(energyRepository.save(any())).thenReturn(energy);

        Energy result = energyService.createProduct(request);

        assertEquals(energy, result);
    }

    @Test
    public void updateProductReturnsUpdatedProduct() {
        UpdateEnergyRequest request = new UpdateEnergyRequest();
        Energy energy = new Energy();
        when(energyRepository.findById(any())).thenReturn(Optional.of(energy));
        when(energyRepository.save(any())).thenReturn(energy);

        Energy result = energyService.updateProduct(request);

        assertEquals(energy, result);
    }

    @Test
    public void findEnergyByNameOrderedByPriceDescReturnsListOfProducts() {
        when(energyRepository.findDistinctByNameAllIgnoreCaseOrderByPriceAsc(any(), any())).thenReturn(Collections.singletonList(new Energy()));

        var result = energyService.findEnergyByNameOrderedByPriceDesc("name", false);

        assertEquals(1, result.size());
    }

    @Test
    public void findEnergyByNameContainsReturnsListOfProducts() {
        when(energyRepository.findByNameContains(any())).thenReturn(Collections.singletonList(new Energy()));

        var result = energyService.findEnergyByNameContains("name");

        assertEquals(1, result.size());
    }
}
