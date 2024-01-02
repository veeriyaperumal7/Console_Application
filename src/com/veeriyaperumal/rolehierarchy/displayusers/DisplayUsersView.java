package com.veeriyaperumal.rolehierarchy.displayusers;

import java.util.ArrayList;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class DisplayUsersView extends BaseView {

	private DisplayUsersViewModel displayUsersViewModel;

	public DisplayUsersView() {
		this.displayUsersViewModel = new DisplayUsersViewModel(this);
	}

	public void viewDisplay() {
		ArrayList<ArrayList<String>> result = displayUsersViewModel.getHierarchyUsers();
		for (ArrayList<String> list : result) {
			println(list.toString());
		}
		printLineSeperator();
	}

}
