/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HMBAO
 */
public class database_helper {

    private String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=ChatJava;encrypt=true;trustServerCertificate=true;";
    private String USER_NAME = "root";
    private String PASSWORD = "root";
    private Connection conn = null;

    public database_helper() {

        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
    }

    public ResultSet select(String queryString) {
        try {
            Statement stmt = conn.createStatement(); // get data from table 'student'
            ResultSet rs = stmt.executeQuery(queryString); // show data
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
