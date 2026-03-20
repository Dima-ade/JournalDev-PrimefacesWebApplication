package com.journaldev.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Listener to initialize and close the database connection
 * when the web application starts and stops.
 */
public class DatabaseInitListener implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource dataSource = buildDataSource(context);

        context.setAttribute("DB_DATASOURCE", dataSource);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!Database connection initialized successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private DataSource buildDataSource(ServletContext context) {
        HikariConfig config = new HikariConfig();

        // Read DB parameters from web.xml context params
        String dbUrl = context.getInitParameter("DB_URL");
        String dbUser = context.getInitParameter("DB_USER");
        String dbPassword = context.getInitParameter("DB_PASSWORD");

        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config); // HikariCP 5.x (uses jakarta.sql.DataSource)
    }
}