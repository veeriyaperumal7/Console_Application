package com.veeriyaperumal.rolehierarchy;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.addrole.AddRoleView;
import com.veeriyaperumal.rolehierarchy.addsubrole.AddSubRoleView;
import com.veeriyaperumal.rolehierarchy.deleterole.DeleteRoleView;

public class RoleHierarchyApp extends BaseView {

	private AddRoleView addRoleView = new AddRoleView();
	private AddSubRoleView addSubRoleView = new AddSubRoleView();
	private DeleteRoleView deleteRoleView = new DeleteRoleView();

	public RoleHierarchyApp() {

	}

	public static void main(String[] args) {
		RoleHierarchyApp app = new RoleHierarchyApp();
		app.start();
	}

	private void start() {
		printHeader("WELOME TO Role Hierarchy");
		showOptions();
	}

	private void showOptions() {
		boolean isExit = false;
		options.clear();
		options.add("Add User");
		options.add("Sub Role");
		options.add("Delete Role");
		options.add("Delete Role");
		options.add("Exit");
		do {
			printOptionsTable(options, "OPTIONS");
			switch (getIntegerInput("Choose given options", 1, 5)) {
			case 1: {
				addRoleView.addRole();
				break;
			}
			case 2: {
				addSubRoleView.addSubRole();
				break;
			}

			case 3: {
				deleteRoleView.deleteRole();
				break;
			}

			case 4: {
				deleteRoleView.deleteRole();
				break;
			}

			case 5: {
				printHeader("THANK YOU");
				isExit = true;
				break;
			}
			}

		} while (!isExit);
	}
}