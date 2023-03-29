package com.example.projet_gestion.DataBase;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCnx {

    // Static fields to store the database connection and configuration
    private static Connection DbLink;
    private static DataBaseConfig config;

    // Constructor to create a new instance of the DataBaseConfig class
    public DataBaseCnx() {
        config = new DataBaseConfig();
    }

    // Static method to get a connection to the database
    public static Connection getCnx() throws SQLException, FileNotFoundException {


        String dbName = "estorymapbd";

            // Get the database URL, username, and password from the configuration
            String url = config.getDatabaseUrl();
            String userName = config.getDatabaseUsername();
            String password = config.getDatabasePassword();

            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                Statement stmt = conn.createStatement();
                String sql = "CREATE DATABASE " + dbName;
                stmt.executeUpdate(sql);
                System.out.println("Database created successfully.");
            } catch (Exception e) {

            }


            // Establish a connection to the database using the JDBC driver
            DbLink = DriverManager.getConnection(url+dbName, userName, password);
            DataBaseCreation.createDatabase(DbLink);
            System.out.println("Connected to Database");




        // Return the database connection object
        return DbLink;
    }
}
