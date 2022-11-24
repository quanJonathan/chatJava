/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import entity.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DAO_FriendList implements DAO<TaiKhoan>{
 final String tableName = "DanhSachBanBe";
    public DAO_FriendList() {
    }

    @Override
    public List<TaiKhoan> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<TaiKhoan> select(String condition) {
        var rs = database_helper.select(database_query_builder.get(tableName, condition, "UsernameBanbe"));
        return resultToList(rs);
    }

    @Override
    public ArrayList<TaiKhoan> resultToList(ResultSet rs) {
        var result = new ArrayList<TaiKhoan>();
        try {
            while (rs.next()) {
                if (rs.getMetaData().getColumnCount() == 1) {
                    result.add(new TaiKhoan(rs.getNString(1), "", ""));
                } else {
                    result.add(new TaiKhoan(
                            rs.getNString(1),
                            rs.getNString(2),
                            rs.getNString(3))
                            .setNgaySinh(rs.getDate(4))
                            .setGioiTinh(rs.getBoolean(5))
                            .setDiaChi(rs.getNString(6))
                            .setTrangThai(rs.getBoolean(7)));
                }
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return result;
        }
    }
    
    @Override
    public ArrayList<TaiKhoan> insert(TaiKhoan t) {
        String insertQuery = t.toDelimitedList();

        var rs = database_helper.insert(database_query_builder.insert(tableName,
                insertQuery
        ));
        return resultToList(rs);
    }

    @Override
    public ArrayList<TaiKhoan> update(TaiKhoan t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(TaiKhoan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
