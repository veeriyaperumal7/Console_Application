package com.veeriyaperumal.rolehierarchy.viewusersandsubusers;

import java.util.ArrayList;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class ViewUsersAndSubUsersView extends BaseView {

	private ViewUsersAndSubUsersViewModel viewUsersAndSubUsersViewModel;

	public ViewUsersAndSubUsersView() {
		this.viewUsersAndSubUsersViewModel = new ViewUsersAndSubUsersViewModel(this);
	}

	public void viewDisplay() {
		ArrayList<ArrayList<String>> result = viewUsersAndSubUsersViewModel.getHierarchyUsers();
		for (ArrayList<String> list : result) {
			println(list.toString());
		}
		printLineSeperator();
	}
	
}
