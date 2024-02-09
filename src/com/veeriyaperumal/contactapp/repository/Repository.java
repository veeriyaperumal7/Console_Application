package com.veeriyaperumal.contactapp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.veeriyaperumal.contactapp.model.User;
import com.veeriyaperumal.contactapp.model.Contact;

public class Repository {

	private static Repository repository;
	private HashMap<String, User> users = new HashMap<>();
	private HashMap<String, List<Contact>> contacts = new HashMap<>();
	private User currentUser;

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean signUp(User user) {
		if (users.containsKey(user.getEmail())) {
			return false;
		}
		users.put(user.getEmail(), new User(user.getEmail(), user.getPassword(), user.getSecret()));
		currentUser = users.get(user.getEmail());
		return true;
	}

	public boolean signIn(User user) {
		currentUser = users.get(user.getEmail());
		return (currentUser == null) ? false : true;
	}

	public List<Contact> getContactList() {
		return contacts.get(currentUser.getEmail());
	}

	public boolean addContacts(Contact contact) {
		List<Contact> contactList = contacts.get(currentUser.getEmail());
		if (contactList == null) {
			contactList = new ArrayList<Contact>();
		}
		contactList.add(new Contact(contact.getEmail(), contact.getName(), contact.getMobileNumber()));
		contacts.put(currentUser.getEmail(), contactList);
		return true;
	}

	public void signOut() {
		currentUser = null;
	}

}
