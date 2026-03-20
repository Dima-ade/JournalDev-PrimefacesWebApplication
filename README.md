Primefaces DataTable with pagination

The application can be deployed on Glassfish server

After deploy:

http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/index.xhtml


PrimeFaces - Date Ranger Picker
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/dateRangePicker.xhtml

PrimeFaces - PickList Example
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/pickList.xhtml

PrimeFaces - SelectOneMenu With Ajax update Example
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/selectOneMenu.xhtml

PrimeFaces - Menu Item
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/menuitem.xhtml

PrimeFaces - Simple File Upload
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/simpleFileUpload.xhtml

PrimeFaces - Login Demo
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/loginDemo.xhtml

PrimeFaces - Employees from DB (uses postgresql database connection)
Database DatabaseInitListener.java listener was created and initialized with values from web.xml
<!-- Context Parameters for DB -->
<context-param>
<param-name>DB_URL</param-name>
<param-value>jdbc:postgresql://localhost:5432/bookstore</param-value>
</context-param>

	<context-param>
		<param-name>DB_USER</param-name>
		<param-value>postgres</param-value>
	</context-param>

	<context-param>
		<param-name>DB_PASSWORD</param-name>
		<param-value>postgres</param-value>
	</context-param>
	<listener>
		<listener-class>com.journaldev.listener.DatabaseInitListener</listener-class>
	</listener>
The bean ViewEmpFromDBManagedBean uses the datasource and entity manager as
        DataSource dataSource = initDatasource();
        Map<String, Object> props = new HashMap<>();
        props.put("javax.persistence.jtaDataSource", dataSource);
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bookstore-PU", props);
        EntityManager em = entityManagerFactory.createEntityManager();
        this.employees = em.createQuery("SELECT b FROM Employee b", Employee.class).getResultList();
The page is:
http://localhost:8080/JournalDev-PrimefacesWebApplication/faces/employees.xhtml
