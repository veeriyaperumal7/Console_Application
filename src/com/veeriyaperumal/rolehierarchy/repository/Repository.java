package com.veeriyaperumal.rolehierarchy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.veeriyaperumal.rolehierarchy.databaseconnection.JdbcConnection;
import com.veeriyaperumal.rolehierarchy.model.Employee;
import com.veeriyaperumal.rolehierarchy.model.Role;

public class Repository {
	private String query;
	private static Repository repository;

	private Repository() {
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public boolean addRole(Role newrole, Employee employee) throws SQLException {
		query = "insert into role_table (role_name) values ('" + newrole.getRoleName() + "')";
		JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
		int roleId = getRoleId(newrole.getRoleName());
		if (roleId != -1) {
			query = "insert into employee (employee_name,role_id) values ('" + employee.getName() + "'," + roleId + ")";
			return (JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1);

		} else {
			return false;
		}

	}

	public boolean addSubRole(Role subRole, Role reportingRole) throws SQLException {
		int subRoleId = -1, reportingRoleId = -1;
		subRoleId = getRoleId(subRole.getRoleName());
		reportingRoleId = getRoleId(reportingRole.getRoleName());
		if (subRoleId == -1) {
			query = "insert into role_table (role_name) values ('" + subRole.getRoleName() + "')";
			JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
			subRoleId = getRoleId(subRole.getRoleName());
		}
		if (reportingRoleId == -1) {
			query = "insert into role_table (role_name) values ('" + reportingRole.getRoleName() + "')";
			JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
			reportingRoleId = getRoleId(reportingRole.getRoleName());
		}

		query = "insert into reporting (reporting_manager_role_id,reporting_role_id) values (" + reportingRoleId + ","
				+ subRoleId + ")";

		return (JdbcConnection.getInstance().executeInsertOrUpdateQuery(query) >= 1);
	}

	public int getRoleId(String roleName) throws SQLException {
		query = "Select role_id from role_table where role_name='" + roleName + "'  and role_status='AVAILABLE'";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result.next()) {
			return result.getInt("role_id");
		} else {
			return -1;
		}
	}

	public String deleteRole(Role role) throws SQLException {
		int currentRoleId = getRoleId(role.getRoleName());
		int reportingId = -1;
		query = "Select reporting_manager_role_id from reporting where reporting_role_id = " + currentRoleId + "";
		ResultSet result = JdbcConnection.getInstance().executeSelectQuery(query);
		if (result.next()) {
			reportingId = result.getInt("reporting_manager_role_id");

			query = "update reporting set reporting_manager_role_id=" + reportingId
					+ " where reporting_manager_role_id=" + currentRoleId;
			JdbcConnection.getInstance().executeInsertOrUpdateQuery(query);
			query = "Select role_name from role_table where role_id=" + reportingId;
			result = JdbcConnection.getInstance().executeSelectQuery(query);
			if (result.next()) {
				return result.getString("role_name");
			} else {
				return "";
			}
		} else {
			return "";
		}

	}
}
