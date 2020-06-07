package com.car.part.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CarType {
    @Id
    @SequenceGenerator(name = "carTypeSequence", sequenceName = "carTypeSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carTypeSequence")
    @Column(name = "carTypeId", unique = true, nullable = false)
    private Long carTypeId;
    private String carTypeName;

    public Long getCarTypeId() {
        return this.carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        return this.carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

}