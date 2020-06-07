package com.car.part.project.repository;

import com.car.part.project.entity.CarLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CarLabelRepository extends JpaRepository<CarLabel, Long> {
    CarLabel findById(long carLabelId);
}