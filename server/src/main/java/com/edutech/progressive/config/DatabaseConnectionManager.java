package com.edutech.progressive.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IllegalStateException("resource.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("spring.datasource.url");
        String user = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        return DriverManager.getConnection(url, user, password);
    }
}
//create table supplier(supplier_id int auto_increment primary key, supplier_name varchar(100)not null, email varchar(100) not null, username varchar(255) not null, password varchar(255) not null, phone varchar(20), address varchar(255), role varchar(50));

//create table warehouse(warehouse_id int primary key auto_increment, supplier_id int not null, warehouse_name varchar(100) not null, location varchar(255)not null, capacity int not null);

//create table product(product_id int primary key auto_increment, warehouse_id int not null, product_name varchar(100)not null, product_description text, quantity int not null, price decimal(10, 2)not null);
