package com.veeriyaperumal.restaurantbilling.onboardview;

import java.sql.SQLException;

import com.veeriyaperumal.restaurantbilling.model.User;
import com.veeriyaperumal.restaurantbilling.repository.Repository;

public class OnboardViewModel {

	private OnboardView onboardView;

	public OnboardViewModel(OnboardView onboardView) {
		this.onboardView = onboardView;
	}

	public boolean isValidUser(User currentUser) throws ClassNotFoundException, SQLException {
		return Repository.getInstance().isValidUser(currentUser);
	}

}
