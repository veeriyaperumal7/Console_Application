package com.veeriyaperumal.contactapp.onboard;

import com.veeriyaperumal.contactapp.model.User;
import com.veeriyaperumal.contactapp.repository.Repository;

public class OnboardViewModel {
	private OnboardView onboardView;

	OnboardViewModel(OnboardView onboardView) {
		this.onboardView = onboardView;
	}

	public boolean signUp(User user) {
		if (!user.getEmail().contains("@") || !user.getEmail().contains(".com") || user.getPassword().length() < 1
				|| user.getSecret().length() < 1) {
			onboardView.printFailureMessage(
					"Check your details...\nEmail address must contains @ and .com ,other data character must have atleast one character.\nTry again...");
			return false;
		}
		if (Repository.getInstance().signIn(user)) {
			return true;
		} else {
			onboardView.printFailureMessage("Email address already present...Try again.");
			return false;
		}

	}

	public boolean signIn(User user) {
		return Repository.getInstance().signUp(user);
	}

}
