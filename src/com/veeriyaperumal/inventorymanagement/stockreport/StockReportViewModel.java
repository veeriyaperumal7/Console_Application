package com.veeriyaperumal.inventorymanagement.stockreport;

import java.util.Map;

import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.repository.Repository;

public class StockReportViewModel {
	StockReportView stockReportView;

	public StockReportViewModel(StockReportView stockReportView) {
		this.stockReportView = stockReportView;
	}

	public Map<Integer, Product> getProductStockList() {
		return Repository.getInstance().getProductStockList();
	}

	public Map<Integer, Product> getProductList() {
		return Repository.getInstance().getProductList();
	}

}
