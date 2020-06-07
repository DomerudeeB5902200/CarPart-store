package com.car.part.project.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.car.part.project.RequeseBody.BillCarPartCarGenerationRequeseBody;
import com.car.part.project.entity.Bill;
import com.car.part.project.entity.BillCarPartCarGeneration;
import com.car.part.project.entity.CarPartCarGeneration;
import com.car.part.project.entity.CarPart;
import com.car.part.project.repository.BillCarPartCarGenerationRepository;
import com.car.part.project.repository.BillRepository;
import com.car.part.project.repository.CarPartCargenerationRepository;
import com.car.part.project.repository.CarPartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CarPartCargenerationRepository carPartCarGenerationRepository;

    @Autowired
    private BillCarPartCarGenerationRepository billCarPartCarGenerationRepository;

    @Autowired
    private CarPartRepository carPartRepository;

    BillController() {
    }

    @GetMapping("/bills")
    public Collection<Bill> getHistoryAll() {
        return billRepository.findAll().stream().collect(Collectors.toList());

    }

    @PostMapping("/save/bill")
    public Bill saveBill(@RequestBody BillCarPartCarGenerationRequeseBody billCarPartCarGenerationRequeseBody) {

        Bill bill = new Bill();
        Integer totalPrice = billCarPartCarGenerationRequeseBody.getTotalPrice();
        bill.setTotalPrice(totalPrice);

        Integer amount = billCarPartCarGenerationRequeseBody.getAmount();
        bill.setAmount(amount);

        Date date = new Date();
        bill.setSellDate(date);

        String cusType = billCarPartCarGenerationRequeseBody.getCusType();
        bill.setCusType(cusType);

        String cusName = billCarPartCarGenerationRequeseBody.getCusName();
        bill.setCusName(cusName);

        bill = billRepository.save(bill);

        // CarLabel carLabelSave =
        // carLabelRepository.findById(carLabel.getCarLabelId().longValue());
        Bill billSave = billRepository.findById(bill.getBillId().longValue());

        billCarPartCarGenerationRequeseBody.getCarPartCarGeneration().forEach(carPart -> {
            CarPartCarGeneration carPartCarGeneration = carPartCarGenerationRepository
                    .findById(carPart.getCarPartCarGenerationId().longValue());

            long carPartId = carPart.getCarPart().getIdPartNo().longValue();
            Integer carPartAmount = carPart.getCarPart().getAmount();
            CarPart carPartOrder = carPartRepository.findById(carPartId);
            carPartOrder.setAmount(carPartOrder.getAmount() - carPartAmount);
            carPartRepository.save(carPartOrder);

            BillCarPartCarGeneration billCarPartCarGeneration = new BillCarPartCarGeneration();
            billCarPartCarGeneration.setBill(billSave);
            billCarPartCarGeneration.setAmount(carPartAmount);
            billCarPartCarGeneration.setCarPartCarGeneration(carPartCarGeneration);
            billCarPartCarGenerationRepository.save(billCarPartCarGeneration);

        });
        return billSave;
    }

    @GetMapping("/searchtotalprice/{startdate}/{enddate}")
    public Integer searchtotalprice(@PathVariable String startdate, @PathVariable String enddate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = LocalDate.parse(startdate);
        LocalDate endDate = LocalDate.parse(enddate);
        Date NewStartDate = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
        Date NewEndDate = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
        return this.billRepository.searchTotalPrice(NewStartDate,NewEndDate);
    }
}