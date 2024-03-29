package com.veeriyaperumal.restaurantbilling.report;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.veeriyaperumal.restaurantbilling.base.BaseViewModel;
import com.veeriyaperumal.restaurantbilling.model.Bill;
import com.veeriyaperumal.restaurantbilling.model.Dish;
import com.veeriyaperumal.restaurantbilling.model.User;
import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class ReportViewModel extends BaseViewModel {

	private ReportView reportView;

	public ReportViewModel(ReportView reportView) {
		this.reportView = reportView;
	}

	public Bill getBill(int billNumber) throws ClassNotFoundException, SQLException {

		return Repository.getInstance().getBill(billNumber);
	}

	public List<User> getEmployeeList() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().getEmployeeList();
	}

	public List<Dish> getDishList() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().getDishList();

	}

	public boolean isValidFromToDate(LocalDate fromDate, LocalDate toDate) {
		return !toDate.isBefore(fromDate);
	}

	public ArrayList<String> getSalesReport(LocalDate fromDate, LocalDate toDate)
			throws ClassNotFoundException, SQLException {
		return Repository.getInstance().getSalesReport(fromDate, toDate);
	}

	public ArrayList<Dish> getDishWiseSalesReport() throws ClassNotFoundException, SQLException {
		return Repository.getInstance().getDishWiseSalesReport();
	}

}
