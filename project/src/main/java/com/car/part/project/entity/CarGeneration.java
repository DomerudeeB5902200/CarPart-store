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
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CarGeneration {
    @Id
    @SequenceGenerator(name = "carGenerationSequence", sequenceName = "carGenerationSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carGenerationSequence")
    @Column(name = "generationCarId", unique = true, nullable = false)
    private Long generationCarId;
    private String generationCarName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarLabel.class)
    @JoinColumn(name = "carLabelId")
    private CarLabel carLabel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarType.class)
    @JoinColumn(name = "carTypeId")
    private CarType carType;

    public CarType getCarType() {
        return this.carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarLabel getCarLabel() {
        return this.carLabel;
    }

    public void setCarLabel(CarLabel carLabel) {
        this.carLabel = carLabel;
    }

    public Long getGenerationCarId() {
        return this.generationCarId;
    }

    public void setGenerationCarId(Long generationCarId) {
        this.generationCarId = generationCarId;
    }

    public String getGenerationCarName() {
        return this.generationCarName;
    }

    public void setGenerationCarName(String generationCarName) {
        this.generationCarName = generationCarName;
    }

}