package com.veeriyaperumal.hotelbilling.billing;

import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.model.Bill;

public class BillingView extends BaseView {

	private Bill currentBill = new Bill();

	public void showBillingOptions() {
		showBillingFeatures();
	}

	private void showBillingFeatures() {
		menuOptions.clear();
		menuOptions.add("Add Dish");
		menuOptions.add("Finish Bill");
		menuOptions.add("Cancel Bill");
		do {
			printCurrentBill();
			printOptionsTable(menuOptions, "Billing Options");
			getIntegerInput("Choose valid options : ", 1, 3);
		} while (true);

	}

	private void printCurrentBill() {
	
		
	}

}
