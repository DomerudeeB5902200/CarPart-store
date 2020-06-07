package com.car.part.project.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import com.car.part.project.entity.CarGeneration;
import com.car.part.project.entity.CarLabel;
import com.car.part.project.entity.CarType;
import com.car.part.project.repository.CarGenerationRepository;
import com.car.part.project.repository.CarLabelRepository;
import com.car.part.project.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CarGenerationController {

    @Autowired
    private CarGenerationRepository carGenerationRepository;

    @Autowired
    private CarTypeRepository carTypeRepository;


    @Autowired
    private CarLabelRepository carLabelRepository;

    CarGenerationController() {
    }

    @GetMapping("/cargenerations")
    public Collection<CarGeneration> getCarGenerationAll() {
        return carGenerationRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/save/cargeneration")
    public CarGeneration saveCarGeneration(@RequestBody CarGeneration carGenerationRequestBody) {
        CarGeneration carGeneration = new CarGeneration();
        carGeneration.setGenerationCarName(carGenerationRequestBody.getGenerationCarName());

        CarType carType = carTypeRepository.findById(carGenerationRequestBody.getCarType().getCarTypeId().longValue());
        carGeneration.setCarType(carType);

        CarLabel carLabel = carLabelRepository.findById(carGenerationRequestBody.getCarLabel().getCarLabelId().longValue());
        carGeneration.setCarLabel(carLabel);
        
        return carGenerationRepository.save(carGeneration);
    }

}