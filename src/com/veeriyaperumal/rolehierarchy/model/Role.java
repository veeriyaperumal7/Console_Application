package com.veeriyaperumal.rolehierarchy.model;

import java.util.ArrayList;

public class Role {
	private int roleId;
	private String roleName, roleStatus, reportingRoleName;
	private ArrayList<Employee> employeeIdList = new ArrayList<>();
	private ArrayList<Role> reportingRoles = new ArrayList<>();

	public ArrayList<Employee> getEmployeeIdList() {
		return employeeIdList;
	}

	public void setEmployeeIdList(ArrayList<Employee> employeeIdList) {
		this.employeeIdList = employeeIdList;
	}

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

	public String getReportingRoleName() {
		return reportingRoleName;
	}

	public void setReportingRoleName(String reportingRoleName) {
		this.reportingRoleName = reportingRoleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public ArrayList<Role> getReportingRoles() {
		return reportingRoles;
	}

	public void setReportingRoles(ArrayList<Role> reportingRoles) {
		this.reportingRoles = reportingRoles;
	}
}
