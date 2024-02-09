package com.veeriyaperumal.contactapp.contact;

import java.util.List;

import com.veeriyaperumal.contactapp.model.Contact;
import com.veeriyaperumal.contactapp.repository.Repository;

public class ContactViewModel {

	private ContactView contactView;

	ContactViewModel(ContactView contactView) {
		this.contactView = contactView;
	}

	List<Contact> getContactList() {
		return Repository.getInstance().getContactList();
	}

	boolean addContacts(Contact contact) {
		return Repository.getInstance().addContacts(contact);
	}

	void signOut() {
		Repository.getInstance().signOut();
	}

}
