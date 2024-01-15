package com.veeriyaperumal.hotelbilling.cancelbill;

import java.sql.SQLException;

import com.veeriyaperumal.hotelbilling.base.BaseView;

public class CancelBillView extends BaseView {

	private CancelBillViewModel cancelBillViewModel;

	public CancelBillView() {
		this.cancelBillViewModel = new CancelBillViewModel(this);
	}

	public void billCancel() {
		print("Enter cancel bill no : ");
		int billNumber = getIntegerInput("Enter valid bill number : ", 1, Integer.MAX_VALUE);
		try {
			if (cancelBillViewModel.billCancel(billNumber)) {
				printSuccesMessage("Bill successfully cancelled...");
			} else {
				printUserWarningMessage("Bill not cancelled.Because given bill number not found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			printErrorMessage("Error while cancelling the bill...");
		}
	}

}
