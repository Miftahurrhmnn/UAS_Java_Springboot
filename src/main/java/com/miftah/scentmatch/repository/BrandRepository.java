package com.miftah.scentmatch.repository;

import com.miftah.scentmatch.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}