package com.example.projet_gestion.DataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseConfig {

    // The name of the configuration file
    private static final String CONFIG_FILE = "config.properties";

    // The properties object that holds the configuration data
    private Properties properties;

    // Constructor that loads the configuration data from the file
    public DataBaseConfig() {
        properties = new Properties();
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the database URL from the configuration
    public String getDatabaseUrl() {
        return properties.getProperty("db.url");
    }

    // Method to get the database username from the configuration
    public String getDatabaseUsername() {
        return properties.getProperty("db.username");
    }

    // Method to get the database password from the configuration
    public String getDatabasePassword() {
        return properties.getProperty("db.password");
    }
}
