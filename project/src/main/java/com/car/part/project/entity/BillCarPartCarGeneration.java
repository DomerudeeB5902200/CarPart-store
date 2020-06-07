package com.car.part.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class BillCarPartCarGeneration {
    @Id
    @SequenceGenerator(name = "billCarPartCarGenerationSequence", sequenceName = "billCarPartCarGenerationSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billCarPartCarGenerationSequence")
    @Column(name = "billCarPartCarGenerationId", unique = true, nullable = false)
    private Long billCarPartCarGenerationId;
    private Integer amount;

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getBillCarPartCarGenerationId() {
        return this.billCarPartCarGenerationId;
    }

    public void setBillCarPartCarGenerationId(Long billCarPartCarGenerationId) {
        this.billCarPartCarGenerationId = billCarPartCarGenerationId;
    }


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarPartCarGeneration.class)
    @JoinColumn(name = "carPartCarGenerationId")
    private CarPartCarGeneration carPartCarGeneration;

    public CarPartCarGeneration getCarPartCarGeneration() {
        return this.carPartCarGeneration;
    }

    public void setCarPartCarGeneration(CarPartCarGeneration carPartCarGeneration) {
        this.carPartCarGeneration = carPartCarGeneration;
    }


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bill.class)
    @JoinColumn(name = "billId")
    private Bill bill;

    public Bill getBill() {
        return this.bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}