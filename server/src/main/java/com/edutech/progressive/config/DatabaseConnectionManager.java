package com.edutech.progressive.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnectionManager {

    private static Properties properties = new Properties();

    static{
        loadProperties();
    }

    private static void loadProperties() {
       
        try(InputStream input = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            if(input==null){
                throw new RuntimeException("application.properties file not found");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load database properties", e);
        }

    }

    public static Connection getConnection(){
        try {
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            String driver = properties.getProperty("spring.datasource.driver-class-name");

            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}


//create table supplier(supplier_id int auto_increment primary key, supplier_name varchar(100)not null, email varchar(100) not null, username varchar(255) not null, password varchar(255) not null, phone varchar(20), address varchar(255), role varchar(50));

//create table warehouse(warehouse_id int primary key auto_increment, supplier_id int not null, warehouse_name varchar(100) not null, location varchar(255)not null, capacity int not null);

//create table product(product_id int primary key auto_increment, warehouse_id int not null, product_name varchar(100)not null, product_description text, quantity int not null, price decimal(10, 2)not null);
