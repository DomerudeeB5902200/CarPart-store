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
public class CarLabel {
    @Id
    @SequenceGenerator(name = "carLabelSequence", sequenceName = "carLabelSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carLabelSequence")
    @Column(name = "carLabelId", unique = true, nullable = false)
    
    private Long carLabelId;
    private String carLabelName;

    public Long getCarLabelId() {
        return this.carLabelId;
    }

    public void setCarLabelId(Long carLabelId) {
        this.carLabelId = carLabelId;
    }

    public String getCarLabelName() {
        return this.carLabelName;
    }

    public void setCarLabelName(String carLabelName) {
        this.carLabelName = carLabelName;
    }

}