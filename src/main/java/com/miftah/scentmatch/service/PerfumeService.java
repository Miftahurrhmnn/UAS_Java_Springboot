package com.miftah.scentmatch.service;

import com.miftah.scentmatch.entity.Brand;
import com.miftah.scentmatch.entity.Category;
import com.miftah.scentmatch.entity.Perfume;
import com.miftah.scentmatch.repository.BrandRepository;
import com.miftah.scentmatch.repository.CategoryRepository;
import com.miftah.scentmatch.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public PerfumeService(PerfumeRepository perfumeRepository,
                          BrandRepository brandRepository,
                          CategoryRepository categoryRepository) {
        this.perfumeRepository = perfumeRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    public Perfume getPerfumeById(Long id) {
        return perfumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfume not found"));
    }

    public Perfume savePerfume(Perfume perfume) {
        if (perfume.getBrand() == null || perfume.getBrand().getId() == null) {
            throw new RuntimeException("Brand harus dipilih");
        }

        if (perfume.getCategory() == null || perfume.getCategory().getId() == null) {
            throw new RuntimeException("Category harus dipilih");
        }

        Brand brand = brandRepository.findById(perfume.getBrand().getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(perfume.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        perfume.setBrand(brand);
        perfume.setCategory(category);

        return perfumeRepository.save(perfume);
    }

    public Perfume updatePerfume(Long id, Perfume updatedPerfume) {
        Perfume existing = getPerfumeById(id);

        if (updatedPerfume.getBrand() == null || updatedPerfume.getBrand().getId() == null) {
            throw new RuntimeException("Brand harus dipilih");
        }

        if (updatedPerfume.getCategory() == null || updatedPerfume.getCategory().getId() == null) {
            throw new RuntimeException("Category harus dipilih");
        }

        Brand brand = brandRepository.findById(updatedPerfume.getBrand().getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(updatedPerfume.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(updatedPerfume.getName());
        existing.setBrand(brand);
        existing.setCategory(category);
        existing.setPrice(updatedPerfume.getPrice());
        existing.setSizeMl(updatedPerfume.getSizeMl());
        existing.setGender(updatedPerfume.getGender());
        existing.setTopNotes(updatedPerfume.getTopNotes());
        existing.setMiddleNotes(updatedPerfume.getMiddleNotes());
        existing.setBaseNotes(updatedPerfume.getBaseNotes());
        existing.setDescription(updatedPerfume.getDescription());
        existing.setImageUrl(updatedPerfume.getImageUrl());
        existing.setStock(updatedPerfume.getStock());

        return perfumeRepository.save(existing);
    }

    public void deletePerfume(Long id) {
        perfumeRepository.deleteById(id);
    }

    public long countPerfumes() {
        return perfumeRepository.count();
    }

    public boolean buyPerfume(Long id) {
        Perfume perfume = getPerfumeById(id);

        if (perfume.getStock() == null || perfume.getStock() <= 0) {
            return false;
        }

        perfume.setStock(perfume.getStock() - 1);
        perfumeRepository.save(perfume);
        return true;
    }
}