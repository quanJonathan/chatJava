/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

/**
 *
 * @author HMBAO
 */
public class database_helper {

    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=ChatJava;encrypt=true;trustServerCertificate=true;";
    private static String USER_NAME = "root";
    private static String PASSWORD = "root";
    private static Connection conn = null;

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

    public static ResultSet select(String queryString) {
        try {
            System.out.println("");
            System.out.println(queryString);

            Statement stmt = conn.createStatement(); // get data from table 'student'
            ResultSet rs = stmt.executeQuery(queryString); // show data
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ResultSet insert(String queryString) throws SQLServerException, Exception {
        try {
            System.out.println("");
            System.out.println(queryString);
            PreparedStatement prepsInsertProduct = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            ResultSet resultSet = prepsInsertProduct.getGeneratedKeys();
            return resultSet;

            // if size = 0  => error
            // Print the ID of the inserted row.
//            while (resultSet.next()) {
//                System.out.println("Generated: " + resultSet.getString(1));
//            }
        } catch (Exception ex) {
            if (ex instanceof SQLServerException sQLServerException) {
                System.out.println(sQLServerException.getSQLState());

                throw ex;
            }
            return null;
        }

    }

    public static int delete(String queryString) {
        try {
            System.out.println("");
            System.out.println(queryString);
            Statement stmt = conn.createStatement();

            return stmt.executeUpdate(queryString);

//            if (x > 0)           
//                System.out.println("Password Successfully delete");           
//            else           
//                System.out.println("ERROR OCCURRED :(");
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static String getColumnName(ResultSet rs, int index) {
        try {
            return rs.getMetaData().getColumnName(index);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
