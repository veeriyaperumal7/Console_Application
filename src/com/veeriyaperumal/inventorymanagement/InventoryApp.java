package com.veeriyaperumal.inventorymanagement;

import java.util.InputMismatchException;

import com.veeriyaperumal.inventorymanagement.addproduct.AddProductView;
import com.veeriyaperumal.inventorymanagement.stockadjustment.StockAdjustmentView;
import com.veeriyaperumal.inventorymanagement.stockreport.StockReportView;
import com.veeriyaperumal.inventorymanagement.updateproduct.UpdateProductDataView;
import com.veeriyaperumal.inventorymanagement.util.Utility;

public class InventoryApp {

	private AddProductView addProductView;
	private StockAdjustmentView stockAdjustmentView;
	private UpdateProductDataView updateProductDataView;
	private StockReportView stockReportView;

	public InventoryApp() {
		this.addProductView = new AddProductView();
		this.stockAdjustmentView = new StockAdjustmentView();
		this.stockReportView = new StockReportView();
		this.updateProductDataView = new UpdateProductDataView();
	}

	public static void main(String[] args) {
		InventoryApp obj = new InventoryApp();
		obj.start();
	}

	private void start() {
		int userInput;
		System.out.println(
				Utility.BOLD + Utility.CYAN + " ====================================================" + Utility.RESET);
		System.out.println(Utility.BOLD + Utility.CYAN + "            " + Utility.ROSECOLOR
				+ "WELCOME TO INVENTORY MANAGEMENT" + Utility.CYAN + "              " + Utility.RESET);
		System.out.println(Utility.BOLD + Utility.CYAN + " ====================================================\n\n"
				+ Utility.RESET);

		do {
			printFeatures();
			userInput = getUserInput(1, 5);
			chooseFeature(userInput);
		} while (userInput != 5);
		System.out.println(
				Utility.BOLD + Utility.CYAN + " ====================================================" + Utility.RESET);
		System.out.println(Utility.BOLD + Utility.CYAN + "      " + Utility.ROSECOLOR
				+ "THANK YOU FOR VISITING US,SEE YOU SOON" + Utility.CYAN + "        ");
		System.out.println(
				Utility.BOLD + Utility.CYAN + " ====================================================" + Utility.RESET);

	}

	private void chooseFeature(int userInput) {
		Utility.printSeperatorLine();
		switch (userInput) {
		case 1:
			addProductView.addProduct();
			break;
		case 2:
           stockAdjustmentView.showStockAdjustmentOptions();
			break;
		case 3:
           updateProductDataView.showProductUpdateOptions();
			break;
		case 4:
			stockReportView.showReport();
			break;
		default:
			break;
		}
	}

	private void printFeatures() {
		System.out.println("1 - Add Product");
		System.out.println("2 - Stock Inward or Stock Outward");
		System.out.println("3 - Delete or Update Product");
		System.out.println("4 - Stock Report");
		System.out.print("5 - Exit\nChoose your option : ");
	}

	private int getUserInput(int minSelection, int maxSelection) {
		int userEnteredChoice = -1;
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (userEnteredChoice < minSelection || userEnteredChoice > maxSelection) {
					Utility.showWrongSelectionMessage("Choose Valid Option : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				Utility.showWrongSelectionMessage("Choose Valid Option : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredChoice;
	}

}
