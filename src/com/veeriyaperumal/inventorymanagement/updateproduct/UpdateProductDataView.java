package com.veeriyaperumal.inventorymanagement.updateproduct;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Set;

import com.veeriyaperumal.inventorymanagement.addproduct.AddProductView;
import com.veeriyaperumal.inventorymanagement.model.Product;
import com.veeriyaperumal.inventorymanagement.util.Utility;

public class UpdateProductDataView extends AddProductView {

	private UpdateProductDataViewModel updateProductDataViewModel;
	private AddProductView addProductView = new AddProductView();

	public UpdateProductDataView() {
		this.updateProductDataViewModel = new UpdateProductDataViewModel(this);
	}

	public void showProductUpdateOptions() {
		chooseOptions();
	}

	private int getUserSelectedOptions() {
		int userEnteredChoice = -1;
		System.out.print("1 - Update Product Details\n2 - Delete Product\nChoose your option : ");
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
		Utility.printSeperatorLine();
		return userEnteredChoice;
	}

	public void chooseOptions() {
		switch (getUserSelectedOptions()) {

		case 1:
			updateProduct();
			break;
		case 2:
			deleteProduct();
			break;
		}
		Utility.printSeperatorLine();
	}

	private void deleteProduct() {
		Product product = showProductData();
		if (updateProductDataViewModel.deleteProduct(product)) {
			Utility.printUserWarningMessage("Product Deleted Successfully");
		} else {
			Utility.printUserWarningMessage("Product Not Deleted");
		}
	}

	private void updateProduct() {
		Product product = showProductData();

		switch (getUpdateOptions()) {
		case "Name":
			updateName(product);
			break;
		case "Price":
			updatePrice(product);
			break;
		case "Unit":
			updateUnit(product);
			break;
		}

	}

	private void updateUnit(Product product) {
		if (updateProductDataViewModel.updateProductUnit(product, addProductView.getProductUnit())) {
			Utility.printUserWarningMessage("Product Units Updated Successfully");
		} else {
			Utility.printUserWarningMessage("Product Units Not Updated");
		}

	}

	private void updatePrice(Product product) {
		if (updateProductDataViewModel.updateProductPrice(product, addProductView.getProductPrice())) {
			Utility.printUserWarningMessage("Product Price Updated Successfully");
		} else {
			Utility.printUserWarningMessage("Product Price Not Updated");
		}

	}

	private void updateName(Product product) {
		if (updateProductDataViewModel.updateProductName(product, addProductView.getProductName())) {
			Utility.printUserWarningMessage("Product Name Updated Successfully");
		} else {
			Utility.printUserWarningMessage("Product Name Not Updated");
		}

	}

	private String getUpdateOptions() {
		int userEnteredChoice = -1;
		System.out.print(
				"1 - Update Product Name\n2 - Update Product Price\n3 - Update Product Units\nChoose your option : ");
		do {
			try {
				userEnteredChoice = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!(userEnteredChoice >= 1 && userEnteredChoice <= 4)) {
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
		switch (userEnteredChoice) {
		case 1:
			return "Name";
		case 2:
			return "Price";
		case 3:
			return "Unit";
		}
		return "";
	}

	private Product showProductData() {
		HashMap<Integer, Product> productList = updateProductDataViewModel.getProductList();
		if (productList.isEmpty()) {
			Utility.printUserWarningMessage("There is no product.First add the product and come back to this option");
			Utility.printSeperatorLine();
			return null;
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
		return product;
	}

	private Product showCurrentProductData(int productId) {
		Utility.printSeperatorLine();
		Product product = updateProductDataViewModel.getProductList().get(productId);
		System.out.println("Product Id        : " + product.getId());
		System.out.println("Product Name      : " + product.getProductName());
		System.out.println("Product Price     : " + product.getPrice());
		System.out.println("Product Unit      : " + product.getUnit());
		Utility.printSeperatorLine();
		return product;
	}

	private int getProductId() {
		int userEnteredProductId = -1;
		System.out.print("Enter the valid product id : ");
		do {
			try {
				userEnteredProductId = Utility.getScanner().nextInt();
				Utility.getScanner().nextLine();
				if (!updateProductDataViewModel.isValidProductId(userEnteredProductId)) {
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

}
