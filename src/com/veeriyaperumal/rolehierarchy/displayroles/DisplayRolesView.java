package com.veeriyaperumal.rolehierarchy.displayroles;

import java.util.ArrayList;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class DisplayRolesView extends BaseView {

	private DisplayRolesViewModel displayUsersViewModel;

	public DisplayRolesView() {
		displayUsersViewModel = new DisplayRolesViewModel(this);
	}

	public void viewDisplay() {
		ArrayList<ArrayList<String>> result = displayUsersViewModel.getHierarchyRoles();
		for(ArrayList<String> list : result) {
			println(list.toString());
		}
		printLineSeperator();
	}

}
