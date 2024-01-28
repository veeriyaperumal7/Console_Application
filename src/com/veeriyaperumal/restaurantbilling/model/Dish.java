package com.veeriyaperumal.restaurantbilling.model;

public class Dish {

	private int dishId, quantity;
	private String dishName, imagePath;
	private float price;

	public Dish() {

	}

	public Dish(Dish dish) {
		this.dishId = dish.dishId;
		this.dishName = dish.dishName;
		this.imagePath = dish.imagePath;
		this.price = dish.price;
		this.quantity = 0;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
