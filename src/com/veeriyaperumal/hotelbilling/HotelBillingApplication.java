package com.veeriyaperumal.hotelbilling;

import com.veeriyaperumal.hotelbilling.onboardview.OnboardView;
import com.veeriyaperumal.hotelbilling.report.ReportView;
import com.veeriyaperumal.hotelbilling.repository.Repository;
import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.billing.BillingView;
import com.veeriyaperumal.hotelbilling.cancelbill.CancelBillView;
import com.veeriyaperumal.hotelbilling.dish.DishView;
import com.veeriyaperumal.hotelbilling.employee.EmployeeView;

public class HotelBillingApplication extends BaseView {

	private OnboardView onboardView;
	private BillingView billingView;
	private EmployeeView employeeView;
	private CancelBillView cancelBillView;
	private ReportView reportView;
	private DishView dishView;

	public HotelBillingApplication() {
		this.onboardView = new OnboardView(this);
		this.billingView = new BillingView();
		this.employeeView = new EmployeeView();
		this.cancelBillView = new CancelBillView();
		this.reportView = new ReportView();
		this.dishView = new DishView();
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
		boolean isExit = false;
		do {
			menuOptions.clear();
			menuOptions.add("Billing");
			menuOptions.add("Reports");
			menuOptions.add("Log out");
			printOptionsTable(menuOptions, "Features");
			print("Choose valid option : ");
			switch (menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1)) {
			case "Billing":
				billingView.showBillingOptions();
				break;
			case "Reports":
				reportView.showReports();
				break;
			case "Log out":
				onboardView.showOnBoardOptions();
				showFeatures();
				isExit = true;
				break;
			}
		} while (!isExit);
	}

	private void showAdminFeatures() {
		boolean isExit = false;
		do {
			menuOptions.clear();
			menuOptions.add("Billing");
			menuOptions.add("Dish");
			menuOptions.add("Employee");
			menuOptions.add("Bill Cancel");
			menuOptions.add("Reports");
			menuOptions.add("Log out");
			printOptionsTable(menuOptions, "Features");
			print("Choose valid option : ");
			switch (menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1)) {
			case "Billing":
				billingView.showBillingOptions();
				break;
			case "Dish":
				dishView.showDishOptions();
				break;
			case "Employee":
				employeeView.showEmployeeOptions();
				break;
			case "Bill Cancel":
				cancelBillView.billCancel();
				break;
			case "Reports":
				reportView.showReports();
				break;
			case "Log out":
				onboardView.showOnBoardOptions();
				showFeatures();
				isExit = true;
				break;
			}
		} while (!isExit);
	}

	public void endApp() {
		printHeader("Thanks for using app,see you again...");
		System.exit(0);
	}

}
