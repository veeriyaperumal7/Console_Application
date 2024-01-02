package com.veeriyaperumal.rolehierarchy.displayusers;

import java.util.ArrayList;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class DisplayUsersViewModel {

	private DisplayUsersView displayUsersView;

	public DisplayUsersViewModel(DisplayUsersView displayUsersView) {
		this.displayUsersView = displayUsersView;
	}
	
	public ArrayList<ArrayList<String>> getHierarchyUsers() {
		return Repository.getInstance().getHierarchyUsers();
	}

}
