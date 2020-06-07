package com.car.part.project.controller;

import java.util.Collection;
import java.util.List;

import com.car.part.project.entity.CarPart;
import com.car.part.project.entity.CarPartCarGeneration;
import com.car.part.project.repository.BillCarPartCarGenerationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class BillCarPartCarGenerationController {

    @Autowired
    private BillCarPartCarGenerationRepository billCarPartCarGenerationRepository;

    BillCarPartCarGenerationController(){}

    @GetMapping("/get/carparts/{billId}")
    public Collection<CarPart> getCarPartByBillId(@PathVariable long billId){
        return billCarPartCarGenerationRepository.findCarPartByBillId(billId);
    }
    @GetMapping("/bestseller")
    public List<Object[]> getBestSeller(){
        return billCarPartCarGenerationRepository.getBestSeller();
    }
}