package com.journaldev.jsfBeans;

import com.journaldev.data.Employee;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.rmi.MarshalledObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class ViewEmpFromDBManagedBean {

	private DataSource dataSource;

	private List<Employee> employees = new ArrayList<Employee>();

	public ViewEmpFromDBManagedBean(){
		init();
		try {
			Connection connection = getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		populateEmployeeList();
	}

	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		this.dataSource = (DataSource) externalContext
				.getApplicationMap()
				.get("DB_DATASOURCE");
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	private void populateEmployeeList() {
		// SQL query to select all records from a table
		String sql = "SELECT employeeid, employeename FROM employees";

		// Use try-with-resources to ensure resources are closed automatically
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			System.out.println("Connected to database successfully.");
			System.out.println("Fetching records from 'employees' table...\n");

			// Process the result set
			while (rs.next()) {
				String id = rs.getString("employeeid");
				String name = rs.getString("employeename");

				System.out.printf("ID: %s | Name: %s%n", id, name);

				Employee emp = new Employee();
				emp.setEmployeeId(id);
				emp.setEmployeeName(name);
				this.employees.add(emp);
			}

		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public int getLength() {
		return employees.size();
	}
}
