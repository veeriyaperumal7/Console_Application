package com.veeriyaperumal.inventorymanagement.stockadjustment;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Set;

import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.util.Utility;

public class StockAdjustmentView {

	private StockAdjustmentViewModel stockAdjustmentViewModel;

	public StockAdjustmentView() {
		this.stockAdjustmentViewModel = new StockAdjustmentViewModel(this);
	}

	public void showStockAdjustmentOptions() {
		String userSelectedOptions;
		userSelectedOptions = getSelectedOption();
		showProductData(userSelectedOptions);
	}

	private void showProductData(String userSelectedOptions) {
		Utility.printSeperatorLine();
		HashMap<Integer, Product> productList = stockAdjustmentViewModel.getProductList();
		if (productList.isEmpty()) {
			Utility.printUserWarningMessage("There is no product.First add the product and come back to this option");
			Utility.printSeperatorLine();
			return;
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-30s | %-15s | %-12s |\n", "Product Id", "Product Name", "Product Price",
				"Product Unit");
		System.out.println("----------------------------------------------------------------------------------");

		Set<Integer> keys = productList.keySet();
		for (Integer productId : keys) {
			Product product = productList.get(productId);
			System.out.printf("| %-12d | %-30s | %-15.2f | %-12s |\n", product.getId(), product.getProductName(),
					product.getPrice(), product.getUnit());
		}

		System.out.println("----------------------------------------------------------------------------------");
		Product product = showCurrentProductData(getProductId());
		if (userSelectedOptions.equals("ADD")) {
			addQuantity(product);
		} else {
			subtractQuantity(product);
		}
	}

	private void subtractQuantity(Product product) {
		Utility.printSeperatorLine();
		if (product.getQuantity() < 1) {
			Utility.printUserWarningMessage("Sorry,unable outward stock because alreay stock is less than 1 quantity.");
			Utility.printSeperatorLine();
			return;
		}
		if (stockAdjustmentViewModel.subtractQuantity(getValidQuantity(product), product)) {
			Utility.printUserWarningMessage("Quantity outward successfully.");
		} else {
			Utility.printUserWarningMessage("Quantity not outward.");
		}
		Utility.printSeperatorLine();
	}

	private void addQuantity(Product product) {
		Utility.printSeperatorLine();
		if (stockAdjustmentViewModel.addQuantity(getValidQuantity(), product)) {
			Utility.printUserWarningMessage("Quantity inward successfully.");
		} else {
			Utility.printUserWarningMessage("Quantity inward added.");
		}
		Utility.printSeperatorLine();
	}

	private int getValidQuantity() {
		int userEnteredProductQuantity = -1;
		System.out.print("Enter the valid product quantity to inward : ");
		do {
			try {
				userEnteredProductQuantity = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!stockAdjustmentViewModel.isValidAddQuantity(userEnteredProductQuantity)) {
					Utility.showWrongSelectionMessage("Product quantity must be greater than zero : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				Utility.showWrongSelectionMessage("Product quantity must be greater than zero : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredProductQuantity;
	}

	private int getValidQuantity(Product product) {
		int userEnteredProductQuantity = -1;
		System.out.print("Enter the valid product quantity to outward : ");
		do {
			try {
				userEnteredProductQuantity = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!stockAdjustmentViewModel.isValidSubtractQuantity(userEnteredProductQuantity, product)) {
					Utility.showWrongSelectionMessage(
							"Entered quantity must be less than equal to current product quantity : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				Utility.showWrongSelectionMessage(
						"Entered quantity must be less than equal to current product quantity : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredProductQuantity;
	}

	private Product showCurrentProductData(int productId) {
		Product product = stockAdjustmentViewModel.getProductStockData(productId);// If already stock present they take
																					// otherwise take just product for
																					// fresh adding
		if (product == null) {
			product = stockAdjustmentViewModel.getProductList().get(productId);
		}
		System.out.println("Product Id        : " + product.getId());
		System.out.println("Product Name      : " + product.getProductName());
		System.out.println("Product Price     : " + product.getPrice());
		System.out.println("Product Unit      : " + product.getUnit());
		System.out.println("Product Quantity  : " + product.getQuantity());
		return product;
	}

	private int getProductId() {
		int userEnteredProductId = -1;
		System.out.print("Enter the valid product id : ");
		do {
			try {
				userEnteredProductId = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!stockAdjustmentViewModel.isValidProductId(userEnteredProductId)) {
					Utility.showWrongSelectionMessage("Choose Valid product id : ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				Utility.showWrongSelectionMessage("Choose Valid product id : ");
				Utility.getScanner().next();
				continue;
			}
		} while (true);
		return userEnteredProductId;
	}

	private String getSelectedOption() {
		int userEnteredChoice = -1;
		System.out.print("1 - Stock Inward\n2 - Stock Outward\nChoose your option : ");
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!(userEnteredChoice >= 1 && userEnteredChoice <= 2)) {
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
		return (userEnteredChoice == 1) ? "ADD" : "SUBTRACT";
	}

}
