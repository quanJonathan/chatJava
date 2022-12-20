/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HMBAO
 */
public class DAO_TaiKhoan implements DAO<TaiKhoan> {

    final String tableName = "TaiKhoan";

    public DAO_TaiKhoan() {

    }

//    @Override
//    public Optional<Account> get(long id) {
//        // return Optional.ofNullable(accounts.get((int) id));     
//
//    }
    @Override
    public ArrayList<TaiKhoan> selectAll() {
        return select("");
    }

    @Override
    public ArrayList<TaiKhoan> select(String condition) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName,condition, ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<TaiKhoan> resultToList(ResultSet rs) throws SQLException {
        var result = new ArrayList<TaiKhoan>();
        while (rs != null && rs.next()) {
            if (rs.getMetaData().getColumnCount() == 1) {
                result.add(new TaiKhoan("", "", ""));
            } else {
                result.add(new TaiKhoan(
                        rs.getNString(1),
                        rs.getNString(2),
                        rs.getNString(4)
                        ).setFullName(rs.getNString(3))
                        .setNgaySinh(rs.getDate(5))
                        .setGioiTinh(rs.getBoolean(6))
                        .setDiaChi(rs.getNString(7))
                        .setTrangThai(rs.getInt(8)));
            }
        }
        return result;

    }

    @Override
    public ArrayList<TaiKhoan> insert(TaiKhoan t) {
        String insertQuery = t.toDelimitedList();
        try {
            var rs = database_helper.insert(database_query_builder.insert(tableName,
                    insertQuery
            ));
            var result = new ArrayList<TaiKhoan>();
            for (int i = 0; i < rs; i++) {
                result.add(new TaiKhoan("", "", ""));
            }
            return result;
        } catch (SQLServerException ex) {
            if (ex.getSQLState().startsWith("23")) {
                System.out.println("Account already exists.");
            }
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    // UNUSED
    public ArrayList<TaiKhoan> update(TaiKhoan t) {
        return new ArrayList<>();
    }
    
    public ArrayList<TaiKhoan> update(TaiKhoan t, String oldUsername) {
        var insertQuery = t.toPair();
        try {
            var rs = database_helper.insert(database_query_builder.update(tableName,
                    insertQuery, "where username=N'" + oldUsername + "'"
            ));
            return new ArrayList<>(rs);
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
    
    public ArrayList<TaiKhoan> updateStatus(TaiKhoan t, int status) {
        try {
            var rs = database_helper.insert("update taikhoan set trangthai='" + status + "' where username=N'" + t.getUsername() + "'");
            return new ArrayList<>(rs);

        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public int delete(TaiKhoan t) {
        return -1;
    }

    public int delete(String conditions) {
        var rs = database_helper.delete(database_query_builder.delete(tableName,
                conditions
        ));
        return rs;
    }
    
    public int deleteUser(String user) {
    database_helper.delete("delete danhsachbanbe where username='" + user + "' or usernameBanBe='" + user + "'" );
    var rs = database_helper.delete("delete taikhoan where username='" + user + "'");
    return rs;
}

}
