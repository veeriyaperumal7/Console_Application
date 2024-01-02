package com.veeriyaperumal.rolehierarchy.viewusersandsubusers;

import java.util.ArrayList;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class ViewUsersAndSubUsersViewModel {

	private ViewUsersAndSubUsersView viewUsersAndSubUsersView;

	public ViewUsersAndSubUsersViewModel(ViewUsersAndSubUsersView viewUsersAndSubUsersView) {
		this.viewUsersAndSubUsersView = viewUsersAndSubUsersView;
	}

	public ArrayList<ArrayList<String>> getHierarchyUsers() {
		return Repository.getInstance().getHierarchyUsersAndSubusers();
	}

}
