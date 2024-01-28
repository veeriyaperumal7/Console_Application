package com.veeriyaperumal.restaurantbilling.report;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.veeriyaperumal.restaurantbilling.base.BaseView;
import com.veeriyaperumal.restaurantbilling.model.Bill;
import com.veeriyaperumal.restaurantbilling.model.Dish;
import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class ReportView extends BaseView {

	private ReportViewModel reportViewModel;

	public ReportView() {
		this.reportViewModel = new ReportViewModel(this);
	}

	public void showReports() {
		if (Repository.getInstance().getCurrentUser().getRole().equals("ADMIN")) {
			showAdminReports();
		} else {
			showUserReports();
		}
	}

	private void showAdminReports() {
		menuOptions.clear();
		menuOptions.add("Employee reports");
		menuOptions.add("Bill wise report");
		menuOptions.add("Dish wise sales reports");
		menuOptions.add("Sales reports");
		menuOptions.add("Dish list reports");
		printOptionsTable(menuOptions, "Reports");
		print("Enter your choice : ");
		chooseReport(menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1));
	}

	private void showUserReports() {
		menuOptions.clear();
		menuOptions.add("Bill wise report");
		menuOptions.add("Dish list reports");
		printOptionsTable(menuOptions, "Reports");
		print("Enter your choice : ");
		chooseReport(menuOptions.get(getIntegerInput("Choose valid options : ", 1, menuOptions.size()) - 1));
	}

	private void chooseReport(String reportName) {
		switch (reportName) {
		case "Bill wise report":
			printBillWiseReport();
			break;
		case "Employee reports":
			printEmployeeReeport();
			break;
		case "Dish wise sales reports":
			printDishWiseSalesReport();
			break;
		case "Sales reports":
			printSalesReport();
			break;
		case "Dish list reports":
			printDishListReport();
			break;
		}
	}

	public void printDishWiseSalesReport() {
		try {
			ArrayList<Dish> dishReport = reportViewModel.getDishWiseSalesReport();
			printDishSalesTable(dishReport, "Dish wise sales report");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while dish wise sales report...");
		}

	}

	private void printSalesReport() {
		LocalDate fromDate = null, toDate = null;
		menuOptions.clear();
		menuOptions.add("Without date");
		menuOptions.add("With date");
		printOptionsTable(menuOptions, "Options");
		print("Enter your choice : ");
		if (getIntegerInput("Enter valid options : ", 1, 2) == 1) {

		} else {
			while (true) {
				print("Note date format must be give like (YYYY-MM-DD)\nEnter from date : ");
				fromDate = getDateInput("Enter valid date format like this (YYYY-MM-DD) : ");
				print("Enter to date : ");
				toDate = getDateInput("Enter valid date format like this (YYYY-MM-DD) : ");
				if (reportViewModel.isValidFromToDate(fromDate, toDate)) {
					break;
				} else {
					printUserWarningMessage(
							"Enter valid from to date.Becasue from date is must be lees than to date...");
				}
			}
		}
		try {
			ArrayList<String> report = reportViewModel.getSalesReport(fromDate, toDate);
			if (report.get(0).equals("0")) {
				printUserWarningMessage("There is no data available...");
			} else {
				println("+" + "-".repeat(48) + "+");
				System.out.printf("| %-20s | %-23s |\n", "Bill Count", "Total Sales Amount");
				println("+" + "-".repeat(48) + "+");
				System.out.printf("| %-20s | %-23s |\n", report.get(0), report.get(1));
				println("+" + "-".repeat(48) + "+");

			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while fetching sales report data...");
		}

	}

	private void printDishListReport() {
		try {
			printDishTable(reportViewModel.getDishList(), "Dishes");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while fetching dish list data...");
		}
	}

	private void printEmployeeReeport() {
		try {
			printUserTable(reportViewModel.getEmployeeList(), "Employee List");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while fetching employee data...");
		}

	}

	private void printBillWiseReport() {
		print("Enter the bill number : ");
		int billNumber = getIntegerInput("Enter valid bill number : ", 1, Integer.MAX_VALUE);
		try {
			Bill bill = reportViewModel.getBill(billNumber);
			if (bill == null) {
				printUserWarningMessage("Given bill not found...");
			} else {
				printBillTable(bill);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while getting bill data...");
		}
	}

}
