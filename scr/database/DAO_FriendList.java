/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

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
public class DAO_FriendList implements DAO<BanBe> {

    final String tableName = "DanhSachBanBe";

    public DAO_FriendList() {
    }

    @Override
    public List<BanBe> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<BanBe> select(String condition) {
        var rs = database_helper.select(database_query_builder.get(tableName, condition, ""));
        return resultToList(rs);
    }

    @Override
    public ArrayList<BanBe> resultToList(ResultSet rs) {
        var result = new ArrayList<BanBe>();
        try {
            while (rs.next()) {
                if (rs.getMetaData().getColumnCount() == 1) {
                    result.add(new BanBe(rs.getNString(1), "", new Date(0)));
                } else {
                    result.add(new BanBe(
                            rs.getNString(1),
                            rs.getNString(2),
                            rs.getDate(3)));
                }
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return result;
        }
    }

    @Override
    public ArrayList<BanBe> insert(BanBe t) {
        String insertQuery = t.toDelimitedList();

        var rs = database_helper.insert(database_query_builder.insert(tableName,
                insertQuery
        ));
        return resultToList(rs);
    }

    @Override
    public ArrayList<BanBe> update(BanBe t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(BanBe t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
