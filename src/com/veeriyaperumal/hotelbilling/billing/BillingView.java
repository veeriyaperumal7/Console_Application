package com.veeriyaperumal.hotelbilling.billing;

import java.sql.SQLException;

import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.model.Bill;

public class BillingView extends BaseView {

	private BillingViewModel billingViewModel;

	public BillingView() {
		this.billingViewModel = new BillingViewModel(this);
	}

	public void showBillingOptions() {
		try {
			billingViewModel.intializeBill();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while getting new bill no...");
			return;
		}
		showBillingFeatures();
	}

	private void showBillingFeatures() {
		boolean isBillCompleted = false;
		menuOptions.clear();
		menuOptions.add("Add Dish");
		menuOptions.add("Remove Dish");
		menuOptions.add("Finish Bill");
		menuOptions.add("Cancel Bill");
		do {
			printCurrentBill();
			printOptionsTable(menuOptions, "Billing Options");
			print("Choose valid option : ");
			switch (menuOptions.get(getIntegerInput("Choose valid options : ", 1, 4) - 1)) {
			case "Add Dish":
				addDish();
				break;
			case "Remove Dish":
				removeDish();
				break;
			case "Finish Bill":
				finishBill();
				isBillCompleted = true;
				break;

			case "Cancel Bill":
				cancelBill();
				isBillCompleted = true;
				break;
			}
		} while (!isBillCompleted);

	}

	private void cancelBill() {
		billingViewModel.cancelCurrentBill();
		printSuccesMessage("Current bill cancelled successfully... ");
	}

	private void finishBill() {
		try {
			if (billingViewModel.finsihBill()) {
				printSuccesMessage("Bill saved successfully...");
			} else {
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printUserWarningMessage("Bill not saved...");
		}
	}

	private void removeDish() {
		if (billingViewModel.getCurrentBill().getPurchasedDish().size() < 1) {
			printUserWarningMessage("There is no product to remove...");
			return;
		}
		print("Enter Serial No : ");
		int serialNo = getIntegerInput("Choose valid Serial no : ", 1,
				billingViewModel.getCurrentBill().getPurchasedDish().size());
		if (billingViewModel.removeDish(serialNo)) {
			printSuccesMessage("Dish removed succesfully...");
		} else {
			printUserWarningMessage("Dish not removed.Because given serial number not found...");
		}
	}

	private void addDish() {

		try {
			printDishTable(billingViewModel.getDishList(), "Dishes");
			print("Enter dish id : ");
			int selectedDishId = getIntegerInput("Choose valid dish id : ");
			print("Enter quantity : ");
			int quantity = getIntegerInput("Enter valid quantity : ", 1, Integer.MAX_VALUE);
			if (billingViewModel.addDishToBill(selectedDishId, quantity)) {
				printSuccesMessage("Dish succesfully added...");
			} else {
				printUserWarningMessage("Dish not added.Because given dish id is not found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while add dish...");
		}

	}

	private void printCurrentBill() {
		printBillTable(billingViewModel.getCurrentBill());
	}

}
