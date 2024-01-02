package com.veeriyaperumal.rolehierarchy.deletetablevalues;

public class DeleteTableValueView {

	private DeleteTableValueViewModel deleteTableValueViewModel;
	
	public DeleteTableValueView() {
		this.deleteTableValueViewModel=new DeleteTableValueViewModel(this);
	}
	
	public void deleteSqlTableData() {
		deleteTableValueViewModel.deleteSqlTableData();
	}
	
}
