package com.veeriyaperumal.hotelbilling.cancelbill;

import java.sql.SQLException;

import com.veeriyaperumal.hotelbilling.repository.Repository;

public class CancelBillViewModel {

	private CancelBillView cancelBillView;

	public CancelBillViewModel(CancelBillView cancelBillView) {
		this.cancelBillView = cancelBillView;
	}

	public boolean billCancel(int billNumber) throws ClassNotFoundException, SQLException {
		return Repository.getInstance().cancelBill(billNumber);
	}

}
