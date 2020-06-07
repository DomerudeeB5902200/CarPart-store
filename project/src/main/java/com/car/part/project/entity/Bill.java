package com.car.part.project.entity;

import java.util.Date;
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
public class Bill {
    @Id
    @SequenceGenerator(name = "billSequence", sequenceName = "billSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billSequence")
    @Column(name = "billId", unique = true, nullable = false)
    private Long billId;
    private Integer totalPrice;
    private Integer amount;
    private Date sellDate;

    private String cusType;
    private String cusName;

    
    public String getCusType() {
        return this.cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

   
    
    public Date getSellDate() {
        return this.sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Long getBillId() {
        return this.billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }


    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    

}