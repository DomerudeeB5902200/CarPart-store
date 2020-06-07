package com.car.part.project.repository;

import com.car.part.project.entity.CarPartCarGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPartCargenerationRepository extends JpaRepository<CarPartCarGeneration, Long>{
    CarPartCarGeneration findById(long carPartCarGenerationId);
}