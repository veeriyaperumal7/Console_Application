package com.veeriyaperumal.restaurantbilling.cancelbill;

import java.sql.SQLException;

import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class CancelBillViewModel {

	private CancelBillView cancelBillView;

	public CancelBillViewModel(CancelBillView cancelBillView) {
		this.cancelBillView = cancelBillView;
	}

	public boolean billCancel(int billNumber) throws ClassNotFoundException, SQLException {
		return Repository.getInstance().cancelBill(billNumber);
	}

}
