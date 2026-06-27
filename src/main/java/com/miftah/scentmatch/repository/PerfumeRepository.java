package com.miftah.scentmatch.repository;

import com.miftah.scentmatch.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
}