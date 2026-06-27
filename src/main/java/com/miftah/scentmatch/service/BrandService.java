package com.miftah.scentmatch.service;

import com.miftah.scentmatch.entity.Brand;
import com.miftah.scentmatch.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public long countBrands() {
        return brandRepository.count();
    }
}