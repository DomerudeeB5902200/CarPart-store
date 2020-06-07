package com.car.part.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.NoArgsConstructor;

@Entity
public class CarPart {

    @Id
    @SequenceGenerator(name = "carPartSequence", sequenceName = "carPartSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carPartSequence")
    @Column(name = "idPartNo", unique = true, nullable = false)
    private Long idPartNo;
    private String carPartName;
    private String passOld;
    private String passNew;
    private Integer priceBuy;
    private Integer priceSell;
    private String company;
    private Integer amount;
    private String detail;

    public CarPart (Integer amount, String passOld, String passNew, String carPartName){
        this.amount=amount;
        this.passOld=passOld;
        this.passNew=passNew;
        this.carPartName=carPartName;
    }
    public CarPart (){}

    public Long getIdPartNo() {
        return this.idPartNo;
    }

    public void setIdPartNo(Long idPartNo) {
        this.idPartNo = idPartNo;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getPriceBuy() {
        return this.priceBuy;
    }

    public void setPriceBuy(Integer priceBuy) {
        this.priceBuy = priceBuy;
    }

    public Integer getPriceSell() {
        return this.priceSell;
    }

    public void setPriceSell(Integer priceSell) {
        this.priceSell = priceSell;
    }

    public String getPassOld() {
        return this.passOld;
    }

    public String getPassNew() {
        return this.passNew;
    }

    public void setPassNew(String passNew) {
        this.passNew = passNew;
    }

    public void setPassOld(String passOld) {
        this.passOld = passOld;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCarPartName() {
        return this.carPartName;
    }

    public void setCarPartName(String carPartName) {
        this.carPartName = carPartName;
    }

}