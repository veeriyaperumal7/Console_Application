package com.veeriyaperumal.contactapp.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.veeriyaperumal.contactapp.model.Contact;

public class ContactView {

	private ContactViewModel contactViewModel;
	private ArrayList<String> options = new ArrayList<String>(List.of("View Contacts", "Add Contacts", "Sign out"));
	private Scanner read = new Scanner(System.in);

	public ContactView() {
		this.contactViewModel = new ContactViewModel(this);
	}

	public void showFeatures() {
		int choosenOption;
		boolean isSignOut = false;
		while (!isSignOut) {
			printFeatures();
			System.out.print("Enter your option");
			choosenOption = read.nextInt();
			read.nextLine();
			switch (options.get(choosenOption - 1)) {
			case "View Contacts": {
				viewContacts();
				break;

			}

			case "Add Contacts": {
				addContacts();
				break;

			}

			case "Sign out": {
				signOut();
				isSignOut = true;

			}

			}
			System.out.println("\n----------------------------------------------------------------------------\n");
		}

	}

	private void signOut() {
		contactViewModel.signOut();
	}

	private void addContacts() {
		Contact contact = new Contact();
		System.out.print("Enter  email : ");
		contact.setEmail(read.nextLine());
		System.out.print("Enter name : ");
		contact.setName(read.nextLine());
		System.out.print("Enter mobile number : ");
		contact.setMobileNumber(read.nextLine());

		if (contactViewModel.addContacts(contact)) {
			System.out.println("Contact added successfully...");
		} else {
			System.out.println("Contact not added...");
		}

	}

	private void viewContacts() {
		List<Contact> contacts = contactViewModel.getContactList();
		int serialNo = 1;
		if (contacts == null || contacts.size() < 1) {
			System.out.println("There is no contacts available...");
			return;
		}
		for (Contact contact : contacts) {
			System.out.println(serialNo++ + " " + contact.toString());
		}

		System.out.println("----------------------------------------------------------------------------");

	}

	private void printFeatures() {
		int serialNo = 1;
		for (String option : options)
			System.out.println(serialNo++ + " " + option);
	}

}
