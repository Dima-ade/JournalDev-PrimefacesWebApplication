package com.journaldev.jsfBeans;

import com.journaldev.data.Employee;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class ViewEmpFromDBManagedBean {
	private final EntityManagerFactory entityManagerFactory;

	private List<Employee> employees;

//	@PersistenceContext(unitName = "bookstore-PU")
//	private EntityManager em;

	public ViewEmpFromDBManagedBean() {
//		employees = em.createQuery("SELECT e FROM Employee e", Employee.class)
//				.getResultList();

		DataSource dataSource = initDatasource();
		Map<String, Object> props = new HashMap<>();
		props.put("javax.persistence.jtaDataSource", dataSource);
		this.entityManagerFactory = Persistence.createEntityManagerFactory("bookstore-PU", props);
		EntityManager em = entityManagerFactory.createEntityManager();
		this.employees = em.createQuery("SELECT b FROM Employee b", Employee.class)
				.getResultList();
	}

	public DataSource initDatasource() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		DataSource dataSource = (DataSource) externalContext
				.getApplicationMap()
				.get("DB_DATASOURCE");
		return dataSource;
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
