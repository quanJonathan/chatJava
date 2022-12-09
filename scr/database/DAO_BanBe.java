/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.BanBe;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DAO_BanBe implements DAO<BanBe> {

    final String tableName = "DanhSachBanBe";

    public DAO_BanBe() {
    }

    @Override
    public List<BanBe> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<BanBe> select(String condition) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName, condition, ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<BanBe> resultToList(ResultSet rs) throws SQLException {
        var result = new ArrayList<BanBe>();
        while (rs.next()) {
            if (rs.getMetaData().getColumnCount() == 1) {
                result.add(new BanBe(rs.getNString(1), "", new Date(0)));
            } else {
                result.add(new BanBe(
                        rs.getNString(1),
                        rs.getNString(2),
                        rs.getTimestamp(3)));
            }
        }
        return result;
    }

    @Override
    public ArrayList<BanBe> insert(BanBe t) {
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
        public int delete(BanBe t) {
        return -1;
    }

    public int delete(String conditions) {
        var rs = database_helper.delete(database_query_builder.delete(tableName,
                conditions
        ));
        return rs;
    }

    @Override
    public ArrayList<BanBe> update(BanBe t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
