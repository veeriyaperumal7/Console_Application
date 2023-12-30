package com.veeriyaperumal.rolehierarchy.model;

public class Role {
	private String roleName, roleStatus;
	private int roleId;

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
