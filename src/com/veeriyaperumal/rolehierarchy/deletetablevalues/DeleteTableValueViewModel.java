package com.veeriyaperumal.rolehierarchy.deletetablevalues;

import com.veeriyaperumal.rolehierarchy.repository.Repository;

public class DeleteTableValueViewModel {

	private DeleteTableValueView deleteTableValueView;

	public DeleteTableValueViewModel(DeleteTableValueView deleteTableValueView) {
		this.deleteTableValueView = deleteTableValueView;
	}

	public void deleteSqlTableData() {
		Repository.getInstance().deleteSqlTableData();
		
	}

}
