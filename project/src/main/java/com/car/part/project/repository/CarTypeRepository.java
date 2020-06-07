package com.car.part.project.repository;

import com.car.part.project.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    CarType findById(long carTypeId);
}