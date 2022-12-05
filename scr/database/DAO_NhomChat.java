/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import entity.NhomChat;
import entity.ThanhVienNhomChat;
import entity.TinNhan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<TinNhan> selectAllMessage(String id) {
        try {
            var messages = new DAO_TinNhan().select("where id=" + id);
            return messages;
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }
    
    public ArrayList<ThanhVienNhomChat> selectAllMembers(String id) {
        try {
            var messages = new DAO_TinNhan().select("where id=" + id);
            return new ArrayList<>();
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
            
            insertQuery = t.toDelimitedList();

            rs = database_helper.insert(database_query_builder.insert(tableName2,
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
    public ArrayList<NhomChat> update(NhomChat t, String conditions) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
