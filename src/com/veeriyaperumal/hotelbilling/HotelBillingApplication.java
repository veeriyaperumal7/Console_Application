package com.veeriyaperumal.hotelbilling;

import com.veeriyaperumal.hotelbilling.onboardview.OnboardView;
import com.veeriyaperumal.hotelbilling.repository.Repository;
import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.billing.BillingView;

public class HotelBillingApplication extends BaseView {

	private OnboardView onboardView = new OnboardView();
	private BillingView billingView;

	public HotelBillingApplication() {
		this.billingView = new BillingView();
	}

	public static void main(String[] args) {
		HotelBillingApplication app = new HotelBillingApplication();
		app.start();
	}

	private void start() {
		printHeader("WELOME TO HOTEL BILLING APPLICATION");
		onboardView.showOnBoardOptions();
		showFeatures();
	}

	private void showFeatures() {
		if (Repository.getInstance().getCurrentUser().getRole().equals("ADMIN")) {
			showAdminFeatures();
		} else {
			showUserFeatures();
		}
	}

	private void showUserFeatures() {

		do {
			menuOptions.clear();
			menuOptions.add("Billing");
			menuOptions.add("Bill Cancel");
			menuOptions.add("Exit");
			printOptionsTable(menuOptions, "Features");
			print("Choose valid option : ");
			switch (menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1)) {
			case "Billing":
				billingView.showBillingOptions();
				break;
			case "Bill Cancel":
				break;
			case "Exit":
				break;
			}
		} while (true);
	}

	private void showAdminFeatures() {
		do {
			menuOptions.clear();
			menuOptions.add("Billing");
			menuOptions.add("Bill Cancel");
			menuOptions.add("Exit");
			printOptionsTable(menuOptions, "Features");
			print("Choose valid option : ");
			switch (menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size() - 1) - 1)) {
			case "Billing":
				billingView.showBillingOptions();
				break;
			case "Bill Cancel":
				break;
			case "Exit":
				break;
			}
		} while (true);
	}

}
