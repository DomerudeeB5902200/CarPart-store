package com.car.part.project.repository;

import com.car.part.project.entity.CarGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CarGenerationRepository extends JpaRepository<CarGeneration, Long> {
    CarGeneration findById(long carGenerationId);
}