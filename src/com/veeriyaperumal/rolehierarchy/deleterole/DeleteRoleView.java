package com.veeriyaperumal.rolehierarchy.deleterole;

import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class DeleteRoleView extends BaseView {

	private DeleteRoleViewModel deleteRoleViewModel;

	public DeleteRoleView() {
		this.deleteRoleViewModel = new DeleteRoleViewModel(this);
	}

	public void deleteRole() {	
		Role deleteRole ,transferRole;
		deleteRole = deleteRoleViewModel.getRole(getName("Enter role to be deleted : "));
		transferRole = deleteRoleViewModel.getRole(getName("Enter  role to be transfered : "));
		
		if(deleteRole==null) {
	    	printUserWarningMessage("Not able to delete role.Because delete role not found...");
	    	return;
	    }
        
	    if(transferRole==null) {
	    	printUserWarningMessage("Not able to delete role.Because transfer role not found...");
	    	return;
	    }
	    
	    
		try {
			if (deleteRoleViewModel.deleteRole(deleteRole,transferRole)) {
				printSuccesMessage("Successfully role deleted...");
			} else {
				printSuccesMessage("Role not deleted...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Role role = new Role();
//		role.setRoleName(getName("Enter the role to deleted : "));
//		String redirectedName = "";
//		try {
//			if (!deleteRoleViewModel.isRolePresent(role.getRoleName())) {
//				printUserWarningMessage("This role not present.");
//				return;
//			}
//			redirectedName = deleteRoleViewModel.deleteRole(role);
//			if (redirectedName.length() >= 1) {
//				printSuccesMessage("Successfully role deleted and redirected to " + redirectedName);
//			} else {
//				printSuccesMessage("Not role deleted");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	private String getName(String message) {
		String name;
		print(message);
		do {
			name = getStringInput("Enter valid name : ");
			if (deleteRoleViewModel.isValidRoleName(name)) {
				break;
			} else {
				continue;
			}
		} while (true);
		return name;
	}
}
