package com.car.part.project.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.car.part.project.entity.CarGeneration;
import com.car.part.project.entity.CarPart;
import com.car.part.project.entity.CarPartCarGeneration;
import com.car.part.project.repository.CarGenerationRepository;
import com.car.part.project.repository.CarPartCargenerationRepository;
import com.car.part.project.repository.CarPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class CarPartController {
    @Autowired
    private CarPartRepository carPartRepository;

    @Autowired
    private CarGenerationRepository carGenerationRepository;

    @Autowired
    private CarPartCargenerationRepository carPartCarGenerationRepository;

    CarPartController() {
    }

    @GetMapping("/carparts")
    public Collection<CarPart> getCarPartAll() {
        return carPartRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/carpartcargenerations") // เตรียมส่งข้อมูลไปให้ตาราง
    public Collection<CarPartCarGeneration> getCarPartCarGenerationAll() {
        return carPartCarGenerationRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/getpartno") // โยนค่า id ล่าสุดไปแสดงช่อง PartNo
    public Long getPartNo() {
        List<CarPartCarGeneration> carpartcargenerations = carPartCarGenerationRepository.findAll();
        return carpartcargenerations.get(carpartcargenerations.size() - 1).getCarPartCarGenerationId() + 1;
    }

    @PostMapping("/save/carpart")
    public CarPartCarGeneration saveCarPart(@RequestBody CarPartCarGeneration carPartCarGenerationRequestBody) {
        CarGeneration carGeneration = carGenerationRepository
                .findById(carPartCarGenerationRequestBody.getCarGeneration().getGenerationCarId().longValue());

        CarPartCarGeneration carPartCarGeneration = new CarPartCarGeneration();
        carPartCarGeneration.setCarGeneration(carGeneration);

        CarPart carPart = new CarPart();
        Integer amount = carPartCarGenerationRequestBody.getCarPart().getAmount();
        String carPartName = carPartCarGenerationRequestBody.getCarPart().getCarPartName();
        String detail = carPartCarGenerationRequestBody.getCarPart().getDetail();
        String passOld = carPartCarGenerationRequestBody.getCarPart().getPassOld();
        String passNew = carPartCarGenerationRequestBody.getCarPart().getPassNew();
        String company = carPartCarGenerationRequestBody.getCarPart().getCompany();
        Integer priceBuy = carPartCarGenerationRequestBody.getCarPart().getPriceBuy();
        Integer priceSell = carPartCarGenerationRequestBody.getCarPart().getPriceSell();

        carPart.setCompany(company);
        carPart.setPassNew(passNew);
        carPart.setPriceSell(priceSell);
        carPart.setPriceBuy(priceBuy);
        carPart.setPassOld(passOld);
        carPart.setAmount(amount);
        carPart.setCarPartName(carPartName);
        carPart.setDetail(detail);
        carPart = carPartRepository.save(carPart); // save แล้วเอาไป set carPartCarGeneration.setCarPart(carPart);
        CarPart carPartSave = carPartRepository.findById(carPart.getIdPartNo().longValue());

        carPartCarGeneration.setCarPart(carPartSave);
        return carPartCarGenerationRepository.save(carPartCarGeneration);
    }

    @PostMapping("/edit/carpart")
    public CarPartCarGeneration editCarPart(@RequestBody CarPartCarGeneration carPartCarGenerationRequestBody) {

        CarPart carPart = carPartRepository
                .findById(carPartCarGenerationRequestBody.getCarPart().getIdPartNo().longValue());

        Integer amount = carPartCarGenerationRequestBody.getCarPart().getAmount();
        String carPartName = carPartCarGenerationRequestBody.getCarPart().getCarPartName();
        String detail = carPartCarGenerationRequestBody.getCarPart().getDetail();
        String passOld = carPartCarGenerationRequestBody.getCarPart().getPassOld();
        String passNew = carPartCarGenerationRequestBody.getCarPart().getPassNew();
        String company = carPartCarGenerationRequestBody.getCarPart().getCompany();
        Integer priceBuy = carPartCarGenerationRequestBody.getCarPart().getPriceBuy();
        Integer priceSell = carPartCarGenerationRequestBody.getCarPart().getPriceSell();

        carPart.setCompany(company);
        carPart.setPassNew(passNew);
        carPart.setPriceSell(priceSell);
        carPart.setPriceBuy(priceBuy);
        carPart.setPassOld(passOld);
        carPart.setAmount(amount);
        carPart.setCarPartName(carPartName);
        carPart.setDetail(detail);
        carPart = carPartRepository.save(carPart);

        CarPartCarGeneration carPartCarGeneration = carPartCarGenerationRepository
                .findById(carPartCarGenerationRequestBody.getCarPartCarGenerationId().longValue());
        CarGeneration carGeneration = carGenerationRepository
                .findById(carPartCarGenerationRequestBody.getCarGeneration().getGenerationCarId().longValue());
        carPartCarGeneration.setCarGeneration(carGeneration);

        carPartCarGeneration = carPartCarGenerationRepository.save(carPartCarGeneration);

        return carPartCarGeneration;
    }

}