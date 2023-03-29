package com.example.projet_gestion.DataBase;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreation {

    static String scriptFilePath="DataBaseCreation.sql";
    public static void createDatabase(Connection conn) throws SQLException, FileNotFoundException {


            try (Statement stmt = conn.createStatement()) {

                BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath));
                String line;
                StringBuilder sqlBuilder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith("--")) {
                        sqlBuilder.append(line.trim());
                        if (line.endsWith(";")) {
                            try {
                                stmt.executeUpdate(sqlBuilder.toString());
                                sqlBuilder.setLength(0);
                            }catch (Exception ignored){
                            }

                        }
                    }
                }
    } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

}