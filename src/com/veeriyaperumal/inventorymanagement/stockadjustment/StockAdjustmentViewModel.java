package com.veeriyaperumal.inventorymanagement.stockadjustment;

import java.util.HashMap;

import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.repository.*;

public class StockAdjustmentViewModel {
	StockAdjustmentView stockAdjustmentView;
	Product product = new Product();
	private HashMap<Integer, Product> productList;
	private HashMap<Integer, Product> productStockList;

	public StockAdjustmentViewModel(StockAdjustmentView stockAdjustmentView) {
		this.stockAdjustmentView = stockAdjustmentView;
	}

	public HashMap<Integer, Product> getProductList() {
		productList = Repository.getInstance().getProductList();
		return productList;
	}

	public HashMap<Integer, Product> getProductStockList() {
		productStockList = Repository.getInstance().getProductStockList();
		return productStockList;
	}

	public boolean isValidProductId(int userEnteredProductId) {
		return productList.containsKey(userEnteredProductId);
	}

	public Product getProductStockData(int productId) {
		if (productStockList == null) {
			getProductStockList();
		}
		return productStockList.get(productId);
	}

	public boolean isValidAddQuantity(int userEnteredProductQuantity) {
		return (userEnteredProductQuantity > 0);
	}

	public boolean isValidSubtractQuantity(int userEnteredProductQuantity, Product product) {
		return (userEnteredProductQuantity <= product.getQuantity());
	}

	public boolean addQuantity(int quantity, Product product) {
		if (Repository.getInstance().addStock(quantity, product)) {
			refreshData();
			return true;
		} else {
			refreshData();
			return false;
		}
	}

	void refreshData() {
		getProductList();
		getProductStockList();
	}

	public boolean subtractQuantity(int quantity, Product product) {
		if (Repository.getInstance().subtractStock(quantity, product)) {
			refreshData();
			return true;
		} else {
			refreshData();
			return false;
		}
	}

}
