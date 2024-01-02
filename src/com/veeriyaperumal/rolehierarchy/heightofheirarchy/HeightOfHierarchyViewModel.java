package com.veeriyaperumal.rolehierarchy.heightofheirarchy;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class HeightOfHierarchyViewModel {

	private HeightOfHierarchyView heightOfHierarchyView;

	public HeightOfHierarchyViewModel(HeightOfHierarchyView heightOfHierarchyView) {
		this.heightOfHierarchyView = heightOfHierarchyView;
	}

	public int getHeightOfHierarchy() {
		return Repository.getInstance().getHeightOfHierarchy();
	}

}
