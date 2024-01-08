package com.veeriyaperumal.inventorymanagement.stockreport;

import java.util.Map;
import java.util.Set;
import java.util.InputMismatchException;

import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.repository.Repository;
import com.veeriyaperumal.inventorymanagement.util.Utility;

public class StockReportView {

	private StockReportViewModel stockReportViewModel;

	public StockReportView() {
		this.stockReportViewModel = new StockReportViewModel(this);
	}

	public void showReport() {
		switch (chooseReport()) {

		case 1:
			showInventoryStockReport();
			break;
		case 2:
			showInventoryDetailedReport();
			break;
		case 3:
			showInventoryProductReport();
			break;
		}
		Utility.printSeperatorLine();
	}

	private void showInventoryProductReport() {
		Utility.printUserWarningMessage("                      INVENTORY PRODUCT REPORT");
		Map<Integer, Product> stockList = stockReportViewModel.getProductList();
		if (stockList.size() < 1) {
			Utility.printUserWarningMessage("There is no data available");
			return;
		}
		Set<Integer> keys = stockList.keySet();
		int count = 0;

		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-30s | %-15s | %-12s |\n", "Product Id", "Product Name", "Product Price",
				"Product Unit");
		System.out.println("----------------------------------------------------------------------------------");

		for (Integer productId : keys) {
			Product product = stockList.get(productId);
			int quantity = product.getQuantity();
			float productTotal = quantity * product.getPrice();
			System.out.printf("| %-12d | %-30s | %-15.2f | %-12s |\n", product.getId(), product.getProductName(),
					product.getPrice(), product.getUnit());
			count++;
		}

		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("| %-63s | %-12d |\n", "Total Number of Products", count);
		System.out.println("----------------------------------------------------------------------------------");
	}

	private void showInventoryDetailedReport() {
		Utility.printUserWarningMessage("                      INVENTORY DETAILED REPORT");
		Map<Integer, Product> stockList = stockReportViewModel.getProductStockList();
		if (stockList.size() < 1) {
			Utility.printUserWarningMessage("There is no data available");
			return;
		}
		Set<Integer> keys = stockList.keySet();
		float totalValue = 0f;
		int totalQuantity = 0;

		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-30s | %-15s | %-12s | %-18s | %-18s |\n", "Product Id", "Product Name",
				"Product Price", "Product Unit", "Product Quantity", "Product Total Value");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");

		for (Integer productId : keys) {
			Product product = stockList.get(productId);
			int quantity = product.getQuantity();
			float productTotal = quantity * product.getPrice();
			totalQuantity += quantity;
			totalValue += productTotal;
			System.out.printf("| %-12d | %-30s | %-15.2f | %-12s | %-18d | %-19.2f |\n", product.getId(),
					product.getProductName(), product.getPrice(), product.getUnit(), quantity, productTotal);
		}

		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-30s | %-15s | %-12s | %-18d | %-19.2f |\n", "", "", "", "", totalQuantity,
				totalValue);
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");
	}

	private void showInventoryStockReport() {
		Utility.printUserWarningMessage("                      INVENTORY STOCK REPORT");
		Map<Integer, Product> stockList = stockReportViewModel.getProductStockList();
		if (stockList.size() < 1) {
			Utility.printUserWarningMessage("There is no data available");
			return;
		}
		Set<Integer> keys = stockList.keySet();
		int totalQuantity = 0;

		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-30s | %-15s | %-12s | %-18s |\n", "Product Id", "Product Name", "Product Price",
				"Product Unit", "Product Quantity");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");

		for (Integer productId : keys) {
			Product product = stockList.get(productId);
			int quantity = product.getQuantity();
			totalQuantity += quantity;
			System.out.printf("| %-12d | %-30s | %-15.2f | %-12s | %-18d |\n", product.getId(),
					product.getProductName(), product.getPrice(), product.getUnit(), quantity);
		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-30s | %-15s | %-12s | %-18d |\n", "", "", "", "", totalQuantity);
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");

	}

	private int chooseReport() {
		int userEnteredChoice = -1;
		System.out.print(
				"1 - Inventory Stock Report\n2 - Inventory Detailed Report\n3 - Inventory Product Report\nChoose your option : ");

		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!(userEnteredChoice >= 1 && userEnteredChoice <= 3)) {
					Utility.showWrongSelectionMessage("Choose Valid option : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				Utility.showWrongSelectionMessage("Choose Valid option : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		Utility.printSeperatorLine();
		return userEnteredChoice;
	}

}
