/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.TinNhan;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HMBAO
 */
public class DAO_TinNhan implements DAO<TinNhan> {

    final String tableName = "TinNhan";

    public DAO_TinNhan() {
    }

    @Override
    public List<TinNhan> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<TinNhan> select(String condition) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName, condition, ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<TinNhan> resultToList(ResultSet rs) throws SQLException {
        var result = new ArrayList<TinNhan>();
//        while (rs.next()) {
//            if (rs.getMetaData().getColumnCount() == 1) {
//                result.add(new TinNhan(rs.getNString(1), "", new Date(0)));
//            } else {
//                result.add(new TinNhan(
//                        rs.getNString(1),
//                        rs.getNString(2),
//                        rs.getDate(3)));
//            }
//        }
        return result;
    }

    @Override
    public ArrayList<TinNhan> insert(TinNhan t) {
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
    public ArrayList<TinNhan> update(TinNhan t, String conditions) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(TinNhan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
