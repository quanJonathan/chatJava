/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.BanBe;
import entity.MaXacNhan;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_MatKhau implements DAO<MaXacNhan> {

    String tableName = "MaXacNhan";

    @Override
    public ArrayList<MaXacNhan> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<MaXacNhan> select(String condition) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName, condition, ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }    }
    
    public ArrayList<MaXacNhan> selectWithID(String id) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName, "where id='" + id + "'", ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }    }

    @Override
    public ArrayList<MaXacNhan> resultToList(ResultSet rs) throws SQLException {
        var result = new ArrayList<MaXacNhan>();
        while (rs.next()) {
            if (rs.getMetaData().getColumnCount() == 1) {
                //result.add(new MaXacNhan(rs.getNString(1), "", new Date(0)));
            } else {
                result.add(new MaXacNhan(
                        rs.getNString(1),
                        rs.getNString(2),
                        rs.getNString(3),
                        rs.getTimestamp(4)));
            }
        }
        return result;
    }

    @Override
    public ArrayList<MaXacNhan> insert(MaXacNhan t) {
        String insertQuery = t.toDelimitedList();

        try {
            var rs = database_helper.insert(database_query_builder.insert(tableName,
                    insertQuery
            ));
            return new ArrayList<>(rs);
        } catch (SQLServerException ex) {
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<MaXacNhan> update(MaXacNhan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(MaXacNhan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
