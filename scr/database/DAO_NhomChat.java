/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.NhomChat;
import entity.TaiKhoan;
import entity.ThanhVienNhomChat;
import entity.TinNhan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HMBAO
 */
public class DAO_NhomChat implements DAO<NhomChat> {

    final String tableName = "NhomChat";
    final String tableName2 = "ThanhVienNhomChat";
    final String chatTable = "TinNhan";

    public DAO_NhomChat() {
    }

    @Override
    public ArrayList<NhomChat> selectAll() {
        return select("");
    }

    public ArrayList<NhomChat> selectAllGroupOfAUser(String user) {
        try {
            var condition = "inner join thanhviennhomchat on nhomchat.idnhom = thanhviennhomchat.idnhom where username=N'" + user + "'";
            var rs = database_helper.select(database_query_builder.get(tableName, condition, "nhomchat.idnhom", "nhomchat.tennhom", "nhomchat.ngaytao"));
            var ars = resultToList(rs);
            ars.sort((NhomChat t1, NhomChat t2) -> t1.getNgayTao().compareTo(t2.getNgayTao()));
            ars.forEach(item -> {
                System.out.println(item);
            });
            return ars;
        } catch (SQLException ex) {
        }
        return new ArrayList<>();
    }

    public ArrayList<ThanhVienNhomChat> selectAllMembers(String id) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName2, "where idNhom=N'" + id + "'", ""));
            var result = new ArrayList<ThanhVienNhomChat>();
            while (rs.next()) {
                if (rs.getMetaData().getColumnCount() == 1) {
                    //
                } else {
                    result.add(new ThanhVienNhomChat(
                            rs.getNString(1),
                            rs.getNString(2),
                            rs.getBoolean(3),
                            rs.getTimestamp(4)));
                }
            }
            return result;
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<NhomChat> select(String condition) {
        try {
            var rs = database_helper.select(database_query_builder.get(tableName, condition, ""));
            return resultToList(rs);
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<NhomChat> resultToList(ResultSet rs) throws SQLException {
        var result = new ArrayList<NhomChat>();
        while (rs.next()) {
            if (rs.getMetaData().getColumnCount() == 1) {
                //
            } else {
                result.add(new NhomChat(
                        rs.getNString(1),
                        rs.getNString(2),
                        rs.getTimestamp(3)));
            }
        }
        return result;
    }

    @Override
    public ArrayList<NhomChat> insert(NhomChat t) {
        try {
            String insertQuery = t.toDelimitedList();

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

    public ArrayList<NhomChat> insertMember(NhomChat t, ArrayList<TaiKhoan> members) {
        try {
            members.forEach(mem -> {
                var m = new ThanhVienNhomChat(t.getIDNhom(), mem.getUsername(), false, new Timestamp(System.currentTimeMillis()));
                var q = m.toDelimitedList();
                try {
                    database_helper.insert(database_query_builder.insert(tableName2, q));
                } catch (Exception ex) {
                }

            });
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public ArrayList<NhomChat> updateAdmin(String id, String user, boolean isAdmin) {
        try {
            var rs = database_helper.insert(
                "update thanhviennhomchat set chucnang='" + isAdmin + "' where username=N'" + user + "' and idNhom=N'" + id + "'"
            );
            return new ArrayList<>(rs);
        } catch (SQLServerException ex) {
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<NhomChat> update(NhomChat t) {
        var insertQuery = t.toPair();
        try {
            var rs = database_helper.insert(database_query_builder.update(tableName,
                    insertQuery, "where idNhom=N'" + t.getIDNhom()+ "'"
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
    public int delete(NhomChat t) {
        return -1;
    }

    public int delete(String conditions) {
        var rs = database_helper.delete(database_query_builder.delete(tableName,
                conditions
        ));
        return rs;
    }
}
