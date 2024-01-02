package com.veeriyaperumal.rolehierarchy.displayroles;

import java.util.ArrayList;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class DisplayRolesViewModel {

	private DisplayRolesView displayUsersView;

	public DisplayRolesViewModel(DisplayRolesView displayUsersView) {
		this.displayUsersView = displayUsersView;
	}

	public ArrayList<ArrayList<String>> getHierarchyRoles() {
		return Repository.getInstance().getHierarchyRoles();
	}

}
