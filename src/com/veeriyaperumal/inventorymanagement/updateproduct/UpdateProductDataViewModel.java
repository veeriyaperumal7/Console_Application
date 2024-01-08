package com.veeriyaperumal.inventorymanagement.updateproduct;

import java.util.HashMap;

import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.repository.Repository;

public class UpdateProductDataViewModel {

	UpdateProductDataView updateProductDataView;
	private HashMap<Integer, Product> productList;

	public UpdateProductDataViewModel(UpdateProductDataView updateProductDataView) {
		this.updateProductDataView = updateProductDataView;
	}

	public HashMap<Integer, Product> getProductList() {
		productList = Repository.getInstance().getProductList();
		return productList;
	}

	public boolean isValidProductId(int userEnteredProductId) {
		return productList.containsKey(userEnteredProductId);
	}

	public boolean updateProductName(Product product, String updatedProductName) {
		return Repository.getInstance().updateProductName(product.getId(), updatedProductName);
	}

	public boolean updateProductPrice(Product product, float updatedProductPrice) {
		return Repository.getInstance().updateProductPrice(product.getId(), updatedProductPrice);
	}

	public boolean updateProductUnit(Product product, String updatedProductUnit) {
		return Repository.getInstance().updateProductUnit(product.getId(), updatedProductUnit);
	}

	public boolean deleteProduct(Product product) {
		return Repository.getInstance().deleteProduct(product.getId());
	}

}
