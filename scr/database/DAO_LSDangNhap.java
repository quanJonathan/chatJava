/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.LichSuDangNhap;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HMBAO
 */
public class DAO_LSDangNhap implements DAO<LichSuDangNhap>{

    
    final String tableName = "LichSuDangNhap";

    public DAO_LSDangNhap() {
    }

    @Override
    public List<LichSuDangNhap> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<LichSuDangNhap> select(String condition) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName, condition, ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<LichSuDangNhap> resultToList(ResultSet rs) throws SQLException {
        var result = new ArrayList<LichSuDangNhap>();
        while (rs.next()) {
            if (rs.getMetaData().getColumnCount() == 1) {
                result.add(new LichSuDangNhap("", new Date(0), new Date(0)));
            } else {
                result.add(new LichSuDangNhap(
                        rs.getNString(1),
                        rs.getTimestamp(2),
                        rs.getTimestamp(3)));
            }
        }
        return result;
    }

    @Override
    public ArrayList<LichSuDangNhap> insert(LichSuDangNhap t) {
        String insertQuery = t.toDelimitedList();

        try {
            var rs = database_helper.insert(database_query_builder.insert(tableName,
                    insertQuery
            ));
            return resultToList(rs);
        } catch (SQLServerException ex) {
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<LichSuDangNhap> update(LichSuDangNhap t, String conditions) {
        var insertQuery = t.toPair();
        try {
            var rs = database_helper.insert(database_query_builder.update(tableName,
                    insertQuery, conditions
            ));
            return resultToList(rs);
        } catch (SQLServerException ex) {
//            if (ex.getSQLState().startsWith("23")) {
//                System.out.println("Account already exists.");
//            }
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int delete(LichSuDangNhap t) {
        return -1;
    }

    public int delete(String conditions) {
        var rs = database_helper.delete(database_query_builder.delete(tableName,
                conditions
        ));
        return rs;
    }

    
}
