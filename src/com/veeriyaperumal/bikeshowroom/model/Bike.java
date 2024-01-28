package com.veeriyaperumal.bikeshowroom.model;

public class Bike {
	private String companyName;
	private String modelName;
	private String bikeSegment;
	private String color;
	private short mileage;
	private short engineCC;
	private int price;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getBikeSegment() {
		return bikeSegment;
	}

	public void setBikeSegment(String bikeSegment) {
		this.bikeSegment = bikeSegment;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public short getMileage() {
		return mileage;
	}

	public void setMileage(short mileage) {
		this.mileage = mileage;
	}

	public short getEngineCC() {
		return engineCC;
	}

	public void setEngineCC(short engineCC) {
		this.engineCC = engineCC;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
