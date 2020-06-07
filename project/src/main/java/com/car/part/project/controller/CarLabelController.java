package com.car.part.project.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import com.car.part.project.entity.CarLabel;
import com.car.part.project.repository.CarLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class CarLabelController {

    @Autowired
    private CarLabelRepository carLabelRepository;

    CarLabelController(){}

    @GetMapping("/carlabels")
    public Collection<CarLabel>getCarLabelAll(){
        return carLabelRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/save/carlabel")
    public CarLabel saveCarLabel(@RequestBody CarLabel carLabelRequestBody) {
        CarLabel carLabel = new CarLabel();
        carLabel.setCarLabelName(carLabelRequestBody.getCarLabelName());
        return carLabelRepository.save(carLabel);
    }
}