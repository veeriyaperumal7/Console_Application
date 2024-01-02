package com.veeriyaperumal.rolehierarchy;

import com.veeriyaperumal.rolehierarchy.base.BaseView;
import com.veeriyaperumal.rolehierarchy.addsubrole.AddSubRoleView;
import com.veeriyaperumal.rolehierarchy.adduser.AddUserView;
import com.veeriyaperumal.rolehierarchy.deleterole.DeleteRoleView;
import com.veeriyaperumal.rolehierarchy.displayroles.DisplayRolesView;
import com.veeriyaperumal.rolehierarchy.rootrole.RootRoleView;
import com.veeriyaperumal.rolehierarchy.viewusersandsubusers.ViewUsersAndSubUsersView;
import com.veeriyaperumal.rolehierarchy.displayusers.DisplayUsersView;
import com.veeriyaperumal.rolehierarchy.heightofheirarchy.HeightOfHierarchyView;
import com.veeriyaperumal.rolehierarchy.numberofuserfromtop.NumberOfUserFromTopView;
import com.veeriyaperumal.rolehierarchy.deleteuser.DeleteUserView;
import com.veeriyaperumal.rolehierarchy.commonboss.CommonBossView;

public class RoleHierarchyApp extends BaseView {

	private RootRoleView rootRoleView = new RootRoleView();
	private AddSubRoleView addSubRoleView = new AddSubRoleView();
	private DisplayRolesView displayRolesView = new DisplayRolesView();
	private DeleteRoleView deleteRoleView = new DeleteRoleView();
	private AddUserView addUserView = new AddUserView();
	private DisplayUsersView displayUsersView = new DisplayUsersView();
	private ViewUsersAndSubUsersView viewUsersAndSubUsersView = new ViewUsersAndSubUsersView();
	private NumberOfUserFromTopView numberOfUserFromTopView = new NumberOfUserFromTopView();
	private HeightOfHierarchyView heightOfHierarchyView = new HeightOfHierarchyView();
	private DeleteUserView deleteUserView = new DeleteUserView();
	private CommonBossView commonBossView = new CommonBossView();

	public RoleHierarchyApp() {

	}

	public static void main(String[] args) {
		RoleHierarchyApp app = new RoleHierarchyApp();
		app.start();
	}

	private void start() {
		printHeader("WELOME TO ROLE HIERARCHY");
		rootRoleView.addRootRole();
		showOptions();
	}

	private void showOptions() {
		boolean isExit = false;
		options.clear();
		options.add("Add Sub Role");
		options.add("Display Roles");
		options.add("Delete Role");
		options.add("Add User");
		options.add("Display Users");
		options.add("View Users and SubUsers");
		options.add("Number of user from top");
		options.add("Heigth of a hirearchy");
		options.add("Delete user");
		options.add("Common boss for user");
		options.add("Exit");
		do {
			printOptionsTable(options, "OPTIONS");
			switch (getIntegerInput("Choose given options", 1, 11)) {
			case 1: {
				addSubRoleView.addSubRole();
				break;
			}
			case 2: {
				displayRolesView.viewDisplay();
				break;
			}

			case 3: {
				deleteRoleView.deleteRole();
				break;
			}

			case 4: {
				addUserView.addUser();
				break;
			}

			case 5: {
				displayUsersView.viewDisplay();
				break;
			}

			case 6: {
				viewUsersAndSubUsersView.viewDisplay();
				break;
			}

			case 7: {
				numberOfUserFromTopView.getNumberOfUserFromTop();
				break;
			}

			case 8: {
				heightOfHierarchyView.getHeightOfHierarchy();
				break;
			}

			case 9: {
				deleteUserView.deleteUser();
				break;
			}

			case 10: {
				commonBossView.getCommonBoss();
				break;
			}

			case 11: {
				printHeader("THANK YOU");
				isExit = true;
				break;
			}
			}

		} while (!isExit);
	}
}