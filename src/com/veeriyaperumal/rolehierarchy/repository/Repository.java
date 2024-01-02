package com.veeriyaperumal.rolehierarchy.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;

import com.veeriyaperumal.rolehierarchy.databaseconnection.JdbcConnection;
import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class Repository {
	private static int roleIdCount = 1, employeeIdCount = 1;
	private String query;
	private static Repository repository;
	private HashMap<Integer, Employee> employees = new HashMap<>();
	private HashMap<String, Role> roles = new HashMap<>();
	private Role headerNode;
	private int rowsAffected = 0;

	private Repository() {
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean addRootRole(Role rootRole) {
		rootRole.setRoleId(roleIdCount++);
		headerNode = rootRole;
		roles.put(rootRole.getRoleName(), rootRole);
		query = "insert into role_table (role_id,role_name) values ( " + rootRole.getRoleId() + ",'"
				+ rootRole.getRoleName() + "')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return rowsAffected >= 1;
	}

	public boolean addSubRole(Role subRole, Role reportingRole) throws SQLException {
		addNewRole(subRole);
		subRole.setReportingRoleName(reportingRole.getRoleName());
		addInHierarchyOrder(subRole, reportingRole);
		query = "insert into reporting (reporting_manager_role_id,reporting_role_id) values ("
				+ reportingRole.getRoleId() + "," + subRole.getRoleId() + ")";

		return (JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1);
	}

	private void addInHierarchyOrder(Role subRole, Role reportingRole) {
		putInHierarchyOrder(subRole, reportingRole, headerNode);
	}

	private void putInHierarchyOrder(Role subRole, Role reportingRole, Role node) {
		if (node.getRoleName().equals(reportingRole.getRoleName())) {
			node.getReportingRoles().add(subRole);
			return;
		}

		if (node.getReportingRoles().size() < 1) {
			return;
		}

		for (int i = 0; i < node.getReportingRoles().size(); i++) {
			putInHierarchyOrder(subRole, reportingRole, node.getReportingRoles().get(i));
		}

	}

	public boolean addNewRole(Role newRole) {
		if (roles.containsKey(newRole.getRoleName())) {
			return false;
		}
		newRole.setRoleId(roleIdCount++);
		roles.put(newRole.getRoleName(), newRole);
		query = "insert into role_table (role_id,role_name) values (" + newRole.getRoleId() + ",'"
				+ newRole.getRoleName() + "')";
		rowsAffected = JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		return rowsAffected >= 1;

	}

	public boolean addUser(Role role, Employee employee) throws SQLException {
		employee.setEmployeeId(employeeIdCount++);
		employees.put(employee.getEmployeeId(), employee);
		role.getEmployeeIdList().add(employee);
		query = "insert into employee (employee_id,employee_name,role_id) values (" + employee.getEmployeeId() + ",'"
				+ employee.getName() + "'," + role.getRoleId() + ")";
		return (JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1);
	}

	public boolean deleteRole(Role deleteRole, Role transferRole) throws SQLException {

		if (deleteRole.getRoleName().equals("CEO")) {
			return false;
		}
		Role reportingRole = getRole(deleteRole.getReportingRoleName());
		for (int i = 0; i < reportingRole.getReportingRoles().size(); i++) {
			if (reportingRole.getReportingRoles().get(i).getRoleName().equals(deleteRole.getRoleName())) {
				reportingRole.getReportingRoles().remove(i);
				break;
			}
		}
		if (!transferRole.getReportingRoleName().equals(deleteRole.getRoleName())) {
			for (int i = 0; i < deleteRole.getReportingRoles().size(); i++) {
				transferRole.getReportingRoles().add(deleteRole.getReportingRoles().get(i));
			}
		} else {

			for (int i = 0; i < deleteRole.getReportingRoles().size(); i++) {
				deleteRole.getReportingRoles().get(i).setReportingRoleName(reportingRole.getRoleName());
				reportingRole.getReportingRoles().add(deleteRole.getReportingRoles().get(i));
			}
			roles.remove(deleteRole.getRoleName());
		}
		roles.remove(deleteRole.getRoleName());
		return true;
	}

	public Role getRole(String roleName) {
		return roles.get(roleName);
	}

	public ArrayList<ArrayList<String>> getHierarchyRoles() {
		ArrayList<ArrayList<String>> hierarchyRoles = new ArrayList<>();
		getHierarchyRoles(hierarchyRoles, headerNode);
		return hierarchyRoles;
	}

	private void getHierarchyRoles(ArrayList<ArrayList<String>> hierarchyRoles, Role node) {
		if (node == null) {
			return;
		}
		Queue<Role> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Role currentRole = queue.poll();
			ArrayList<String> list = new ArrayList<>();
			list.add(currentRole.getRoleName() + "->");
			for (Role role : currentRole.getReportingRoles()) {
				list.add(role.getRoleName());
				queue.add(role);
			}
			hierarchyRoles.add(list);
		}
	}

	public ArrayList<ArrayList<String>> getHierarchyUsers() {
		ArrayList<ArrayList<String>> hierarchyUsers = new ArrayList<>();
		getHierarchyUsers(hierarchyUsers, headerNode);
		return hierarchyUsers;
	}

	private void getHierarchyUsers(ArrayList<ArrayList<String>> hierarchyUsers, Role node) {
		if (node == null) {
			return;
		}
		Queue<Role> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Role currentRole = queue.poll();
			ArrayList<String> list = new ArrayList<>();
			list.add(currentRole.getRoleName() + "->");
			for (int i = 0; i < currentRole.getEmployeeIdList().size(); i++) {
				list.add(currentRole.getEmployeeIdList().get(i).getName());
			}
			for (Role role : currentRole.getReportingRoles()) {
				queue.add(role);
			}
			hierarchyUsers.add(list);
		}
	}

	public ArrayList<ArrayList<String>> getHierarchyUsersAndSubusers() {
		ArrayList<ArrayList<String>> hierarchyUsersAndSubusers = new ArrayList<>();
		Role node = headerNode;
		if (node == null) {
			return null;
		}
		Queue<Role> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Role currentRole = queue.poll();
			ArrayList<String> list = new ArrayList<>();
			getSubUsers(currentRole, list);
			if (list.size() >= 1)
				hierarchyUsersAndSubusers.add(list);
			for (Role role : currentRole.getReportingRoles()) {
				queue.add(role);
			}
		}

		return hierarchyUsersAndSubusers;
	}

	private void getSubUsers(Role node, ArrayList<String> list) {
		if (node == null) {
			return;
		}
		Queue<Role> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Role currentRole = queue.poll();
			for (int i = 0; i < currentRole.getEmployeeIdList().size(); i++) {
				list.add(currentRole.getEmployeeIdList().get(i).getName());
			}
			for (Role role : currentRole.getReportingRoles()) {
				queue.add(role);
			}
		}

	}

	public boolean deleteUser(String employeeName) {
		for (Map.Entry<String, Role> entry : roles.entrySet()) {
			Role role = entry.getValue();
			for (int i = 0; i < role.getEmployeeIdList().size(); i++) {
				if (role.getEmployeeIdList().get(i).getName().equals(employeeName)) {
					int id = role.getEmployeeIdList().get(i).getEmployeeId();
					role.getEmployeeIdList().remove(i);
					employees.remove(id);
					return true;
				}
			}
		}
		return false;
	}

	public int getNumberOfUserFromTop(String employeeName) {
		String roleName = getEmployeeRoleName(employeeName);
		if (roleName == null) {
			return -1;
		}
		int count[] = new int[1];
		count[0] = -1;
		getTopCount(headerNode, count, 0, roleName);
		return count[0];
	}

	private String getEmployeeRoleName(String employeeName) {
		for (Map.Entry<String, Role> entry : roles.entrySet()) {
			Role role = entry.getValue();
			for (int i = 0; i < role.getEmployeeIdList().size(); i++) {
				if (role.getEmployeeIdList().get(i).getName().equals(employeeName)) {
					return role.getRoleName();
				}
			}
		}
		return null;
	}

	private void getTopCount(Role node, int count[], int height, String roleName) {

		if (node.getRoleName().equals(roleName)) {
			count[0] = height;
			return;
		}
		if (node.getReportingRoles().size() < 1) {
			return;
		} else {
			for (int i = 0; i < node.getReportingRoles().size(); i++) {
				getTopCount(node.getReportingRoles().get(i), count, height + 1, roleName);
			}
		}

	}

	public int getHeightOfHierarchy() {
		int height[] = { 1 };
		getHeight(headerNode, 0, height);
		return height[0];
	}

	private void getHeight(Role node, int count, int[] height) {
		if (node.getReportingRoles().size() < 1) {
			height[0] = Math.max(count, height[0]);
			return;
		}
		for (int i = 0; i < node.getReportingRoles().size(); i++) {
			getHeight(node.getReportingRoles().get(i), count + 1, height);
		}

	}

	public String getCommonBoss(String employeeName1, String employeeName2) {
		ArrayList<String> bossList1 = new ArrayList<>();
		ArrayList<String> bossList2 = new ArrayList<>();
		String employee1RoleName = getEmployeeRoleName(employeeName1);
		String employee2RoleName = getEmployeeRoleName(employeeName2);
		getBoss(bossList1, new ArrayList<String>(), employee1RoleName, headerNode);
		getBoss(bossList2, new ArrayList<String>(), employee2RoleName, headerNode);
		int minSize = Math.min(bossList1.size(), bossList2.size());
		String commonName = "";
		for (int i = 0; i < minSize; i++) {
			if (bossList1.get(i).equals(bossList2.get(i)) && !bossList1.get(i).equals(employeeName1)
					&& !bossList1.get(i).equals(employeeName2) && !bossList2.get(i).equals(employeeName1)
					&& !bossList2.get(i).equals(employeeName2)) {
				commonName = bossList1.get(i);
			}
		}

		return commonName;
	}

	private void getBoss(ArrayList<String> bossList, ArrayList<String> arrayList, String employeeRoleName, Role node) {
		if (node.getRoleName().equals(employeeRoleName)) {
			arrayList.add(node.getEmployeeIdList().get(0).getName());
			bossList.addAll(new ArrayList<>(arrayList));
			return;
		}

		for (int i = 0; i < node.getReportingRoles().size(); i++) {
			arrayList.add(node.getEmployeeIdList().get(0).getName());
			getBoss(bossList, arrayList, employeeRoleName, node.getReportingRoles().get(i));
			arrayList.remove(arrayList.size() - 1);
		}

	}

	public void deleteSqlTableData() {
		query = "truncate TABLE reporting";
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

		query = "truncate TABLE employee";
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

		query = "truncate TABLE role_table";
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);

	}

}
