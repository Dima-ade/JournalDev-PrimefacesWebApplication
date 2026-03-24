package com.journaldev.jsfBeans;

import com.journaldev.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ViewEmployeesManagedBean {

	private static final Logger logger = LoggerFactory.getLogger(ViewEmployeesManagedBean.class);
	private List<Employee> employees = new ArrayList<Employee>();
	
	public ViewEmployeesManagedBean(){
		logger.info("=====================ViewEmployeesManagedBean===========================");
		populateEmployeeList();
	}

	private void populateEmployeeList(){
		for(int i = 1 ; i <= 10 ; i++){
			Employee emp = new Employee();
			emp.setEmployeeId(String.valueOf(i));
			emp.setEmployeeName("Employee#"+i);
			this.employees.add(emp);
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
