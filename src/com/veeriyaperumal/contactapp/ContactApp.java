package com.veeriyaperumal.contactapp;

import com.veeriyaperumal.contactapp.contact.ContactView;
import com.veeriyaperumal.contactapp.onboard.OnboardView;

public class ContactApp {

	public static void main(String[] args) {
		ContactApp app = new ContactApp();
		System.out.println("Welcome to Contact App");
		app.start();
	}

	private void start() {
		OnboardView onboardView = new OnboardView();
		ContactView contactView = new ContactView();
		boolean isExit = false;
		do {
			isExit = onboardView.showOnboardOption();
			contactView.showFeatures();
		} while (!isExit);
		System.out.println("Thanks for using this app");

	}

}
