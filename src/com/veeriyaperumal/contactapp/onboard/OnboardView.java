package com.veeriyaperumal.contactapp.onboard;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.veeriyaperumal.contactapp.model.User;

public class OnboardView {

	private OnboardViewModel onboardViewModel;
	private ArrayList<String> options = new ArrayList<String>(List.of("Sign in", "Sign up", "Exit"));
	private Scanner read = new Scanner(System.in);

	public OnboardView() {
		this.onboardViewModel = new OnboardViewModel(this);
	}

	public boolean showOnboardOption() {
		int choosenOption;
		printFeatures();
		System.out.print("Enter your option : ");
		choosenOption = read.nextInt();
		read.nextLine();
		System.out.println("\n----------------------------------------------------------------------------\n");
		switch (options.get(choosenOption - 1)) {
		case "Sign in": {
			signIn();
			System.out.println("\n----------------------------------------------------------------------------\n");
			return false;
		}

		case "Sign up": {
			signUp();
			System.out.println("\n----------------------------------------------------------------------------\n");
			return false;
		}

		case "Exit": {
			System.out.println("\n----------------------------------------------------------------------------\n");
			return true;

		}

		}

		return false;
	}

	private void signUp() {
		User user = new User();
		while (true) {
			System.out.print("Enter your email : ");
			user.setEmail(read.nextLine());
			System.out.print("Enter your password : ");
			user.setPassword(read.nextLine());
			System.out.print("Enter your secret : ");
			user.setSecret(read.nextLine());
			if (onboardViewModel.signIn(user)) {
				System.out.println("Sign in successfully...");
				break;
			}
		}
	}

	private void signIn() {

		User user = new User();
		while (true) {
			System.out.print("Enter your email : ");
			user.setEmail(read.nextLine());
			System.out.print("Enter your password : ");
			user.setPassword(read.nextLine());

			if (onboardViewModel.signUp(user)) {
				System.out.println("Sign up successfully...");
				break;
			} else {
				System.out.println("Sign up failed.Try again..");
			}

		}

	}

	private void printFeatures() {
		int serialNo = 1;
		for (String option : options)
			System.out.println(serialNo++ + " " + option);
	}

	void printFailureMessage(String message) {
		System.out.println(message);
	}

}
