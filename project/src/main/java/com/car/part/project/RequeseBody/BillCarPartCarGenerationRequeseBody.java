package com.car.part.project.RequeseBody;

import java.util.ArrayList;


import com.car.part.project.entity.CarPartCarGeneration;

public class BillCarPartCarGenerationRequeseBody {

    private Integer totalPrice;
	private Integer amount;
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



    private ArrayList<CarPartCarGeneration> carPartCarGeneration;

    public ArrayList<CarPartCarGeneration> getCarPartCarGeneration() {
        return this.carPartCarGeneration;
    }

    public void setCarPartCarGeneration(ArrayList<CarPartCarGeneration> carPartCarGeneration) {
        this.carPartCarGeneration = carPartCarGeneration;
    }

   

    

}