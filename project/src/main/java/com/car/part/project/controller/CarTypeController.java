package com.car.part.project.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import com.car.part.project.entity.CarType;
import com.car.part.project.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CarTypeController {
    @Autowired
    private CarTypeRepository carTypeRepository;

    CarTypeController() {}

    @GetMapping("/cartypes")
    public Collection<CarType> getCarTypeAll() {
        return carTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("save/cartype")
    public CarType saveCarType(@RequestBody CarType carTypeRequesBody){
        CarType carType = new CarType();
        carType.setCarTypeName(carTypeRequesBody.getCarTypeName());
        return carTypeRepository.save(carType);
    }
}