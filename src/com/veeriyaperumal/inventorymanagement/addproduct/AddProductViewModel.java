package com.veeriyaperumal.inventorymanagement.addproduct;

import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.repository.Repository;

public class AddProductViewModel {

	private AddProductView addProductView;
	private Product product;

	public AddProductViewModel(AddProductView addProductView) {
		this.addProductView = addProductView;
		this.product = new Product();
	}

	public Product getProduct() {
		return product;
	}

	public boolean isValidProductName(String userEnteredProduct) {
		if (userEnteredProduct.length() < 1) {
			return false;
		}
		return true;
	}

	public boolean isValidProductUnit(String userEnteredProductUnit) {
		if (userEnteredProductUnit.length() < 1) {
			return false;
		}
		return true;
	}

	public boolean isValidProductPrice(float userEnteredPrice) {
		if (userEnteredPrice < 1) {
			return false;
		}
		return true;
	}

	boolean addProduct() {
		return Repository.getInstance().addProduct(product);
	}

}
