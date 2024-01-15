package com.veeriyaperumal.hotelbilling.onboardview;

import java.sql.SQLException;

import com.veeriyaperumal.hotelbilling.HotelBillingApplication;
import com.veeriyaperumal.hotelbilling.base.BaseView;
import com.veeriyaperumal.hotelbilling.model.User;

public class OnboardView extends BaseView {

	private OnboardViewModel onboardViewModel;
	private HotelBillingApplication hotelBillingApplication;

	public OnboardView() {
		onboardViewModel = new OnboardViewModel(this);
	}

	public OnboardView(HotelBillingApplication hotelBillingApplication) {
		this.hotelBillingApplication = hotelBillingApplication;
		onboardViewModel = new OnboardViewModel(this);
	}

	public void showOnBoardOptions() {
		menuOptions.clear();
		menuOptions.add("Login");
		menuOptions.add("Exit");
		printOptionsTable(menuOptions, "OPTIONS");
		print("Choose your option : ");
		chooseOptions(menuOptions.get(getIntegerInput("Choose Valid Options : ", 1, menuOptions.size()) - 1));
	}

	private void chooseOptions(String selectedOption) {
		switch (selectedOption) {
		case "Login": {
			showLoginOption();
			break;
		}
		case "Exit": {
			hotelBillingApplication.endApp();
		}
		}

	}

	private void showLoginOption() {
		User currentUser = new User();

		while (true) {
			print("Enter your email address : ");
			currentUser.setEmailAddress(getEmailInput("Enter valid email input :"));
			print("Enter your password : ");
			currentUser.setPassword(getStringInput("Enter valid password :"));
			try {
				if (onboardViewModel.isValidUser(currentUser)) {
					printSuccesMessage("Login Successfull...");
					return;
				} else {
					printUserWarningMessage("Email address or password is wrong.Try again...");
					continue;
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
