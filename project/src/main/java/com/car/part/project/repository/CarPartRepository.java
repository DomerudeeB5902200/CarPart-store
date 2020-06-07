package com.car.part.project.repository;

import com.car.part.project.entity.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CarPartRepository extends JpaRepository<CarPart, Long>{
    CarPart findById(long IdPartNo);
}