package com.journaldev.jsfBeans;

import com.journaldev.data.Employee;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class ViewEmployeeManagedBeanTest {

    private ViewEmployeesManagedBean viewEmployeeManagedBean;


    @Before
    public void setUp() {
        viewEmployeeManagedBean = new ViewEmployeesManagedBean();
        System.out.println("Setup complete.");
    }

    @Test
    public void testEmployeesAndSetters() {
        Employee person = new Employee();
        person.setEmployeeName("Alice");
        person.setEmployeeId("25");

        assertEquals("Alice", person.getEmployeeName());
        assertEquals("25", person.getEmployeeId());
    }

    @Test
    public void testListEmployees() {
        List<Employee> empList = viewEmployeeManagedBean.getEmployees();

        assertEquals(10, empList.size());
        assertEquals("Employee#5", empList.get(4).getEmployeeName());
        assertEquals("5", empList.get(4).getEmployeeId());
    }
}
