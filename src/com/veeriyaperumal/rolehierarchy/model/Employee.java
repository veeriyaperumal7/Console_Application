package com.veeriyaperumal.rolehierarchy.model;

import java.util.ArrayList;

public class Employee {
	private String name, activeStatus;
	private int employeeId, roleId, reportManagerId;
	private ArrayList<Employee> reportingList = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getReportManagerId() {
		return reportManagerId;
	}

	public void setReportManagerId(int reportManagerId) {
		this.reportManagerId = reportManagerId;
	}

	public ArrayList<Employee> getReportingList() {
		return reportingList;
	}

	public void setReportingList(ArrayList<Employee> reportingList) {
		this.reportingList = reportingList;
	}
}
