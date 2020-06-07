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
public class CarPartCarGeneration {
    @Id
    @SequenceGenerator(name = "carPartCarGenerationSequence", sequenceName = "carPartCarGenerationSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carPartCarGenerationSequence")
    @Column(name = "carPartCarGenerationId", unique = true, nullable = false)
    private Long carPartCarGenerationId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarPart.class)
    @JoinColumn(name = "IdPartNo")
    private CarPart carPart;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarGeneration.class)
    @JoinColumn(name = "generationCarId")
    private CarGeneration carGeneration;

    public Long getCarPartCarGenerationId() {
        return this.carPartCarGenerationId;
    }

    public void setCarPartCarGenerationId(Long carPartCarGenerationId) {
        this.carPartCarGenerationId = carPartCarGenerationId;
    }
    
    public CarGeneration getCarGeneration() {
        return this.carGeneration;
    }

    public void setCarGeneration(CarGeneration carGeneration) {
        this.carGeneration = carGeneration;
    }

    public CarPart getCarPart() {
        return this.carPart;
    }

    public void setCarPart(CarPart carPart) {
        this.carPart = carPart;
    }
}