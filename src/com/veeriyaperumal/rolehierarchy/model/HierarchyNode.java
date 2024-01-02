package com.veeriyaperumal.rolehierarchy.model;

import java.util.ArrayList;

public class HierarchyNode {

	private Role role;
	private ArrayList<HierarchyNode> subRoles = new ArrayList<>();

	public ArrayList<HierarchyNode> getsubRoles() {
		return subRoles;
	}

	public void setSubRoleId(ArrayList<HierarchyNode> subRoles) {
		this.subRoles = subRoles;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
