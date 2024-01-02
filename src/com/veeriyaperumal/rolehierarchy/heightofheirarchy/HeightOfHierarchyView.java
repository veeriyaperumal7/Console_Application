package com.veeriyaperumal.rolehierarchy.heightofheirarchy;

import com.veeriyaperumal.rolehierarchy.base.BaseView;

public class HeightOfHierarchyView extends BaseView {

	private HeightOfHierarchyViewModel heightOfHierarchyViewModel;

	public HeightOfHierarchyView() {
		this.heightOfHierarchyViewModel = new HeightOfHierarchyViewModel(this);
	}

	public void getHeightOfHierarchy() {
		printSuccesMessage("The height of a hierarchy is : " + heightOfHierarchyViewModel.getHeightOfHierarchy());
	}
}
