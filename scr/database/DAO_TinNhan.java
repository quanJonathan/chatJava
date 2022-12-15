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
    final String tableName2 = "Danhsachtinnhan";

    public DAO_TinNhan() {
    }

    @Override
    public ArrayList<TinNhan> selectAll() {
        return select("");
    }

    public ArrayList<TinNhan> selectAll(String sender, String receiver) {
        try {
            var condition = "inner join danhsachtinnhan on tinnhan.id = danhsachtinnhan.id where bansao='" + sender + "'" + " and((danhsachtinnhan.nguoigui = N'" + sender + "' and danhsachtinnhan.nguoinhan = N'" + receiver + "')" + " or (danhsachtinnhan.nguoinhan = N'" + sender + "'" + " and danhsachtinnhan.nguoigui = N'" + receiver + "'))";
            var rs = database_helper.select(database_query_builder.get(tableName, condition, "tinnhan.id", "tinnhan.thoiGian", "tinnhan.noidung", "danhsachtinnhan.nguoigui", "danhsachtinnhan.nguoinhan", "danhsachtinnhan.idnhom", "banSao"));
            var ars = resultToList(rs);
            ars.sort((TinNhan t1, TinNhan t2) -> t1.getThoiGian().compareTo(t2.getThoiGian()));
            ars.forEach(item->{
                System.out.println(item);
            });
            return ars;
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }
    
    public ArrayList<TinNhan> searchFromAUser(String sender, String receiver, String text) {
        try {
            var condition = "inner join danhsachtinnhan on tinnhan.id = danhsachtinnhan.id " + text + " and bansao='" + sender + "' and idNhom is null " + " and((danhsachtinnhan.nguoigui = N'" + sender + "' and danhsachtinnhan.nguoinhan = N'" + receiver + "')" + " or (danhsachtinnhan.nguoinhan = N'" + sender + "'" + " and danhsachtinnhan.nguoigui = N'" + receiver + "')) ";
            var rs = database_helper.select(database_query_builder.get(tableName, condition, "tinnhan.id", "tinnhan.thoiGian", "tinnhan.noidung", "danhsachtinnhan.nguoigui", "danhsachtinnhan.nguoinhan", "danhsachtinnhan.idnhom", "banSao"));
            var ars = resultToList(rs);
            ars.sort((TinNhan t1, TinNhan t2) -> t1.getThoiGian().compareTo(t2.getThoiGian()));
            ars.forEach(item->{
                System.out.println(item);
            });
            return ars;
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
    }
    
    public ArrayList<TinNhan> searchFromAllUser(String sender, String text) {
        try {
            var condition = "inner join danhsachtinnhan on tinnhan.id = danhsachtinnhan.id " + text + " and bansao='" + sender + "' " + " and (danhsachtinnhan.nguoigui = N'" + sender + "'"  + " or danhsachtinnhan.nguoinhan = N'" + sender + "') ";
            var rs = database_helper.select(database_query_builder.get(tableName, condition, "tinnhan.id", "tinnhan.thoiGian", "tinnhan.noidung", "danhsachtinnhan.nguoigui", "danhsachtinnhan.nguoinhan", "danhsachtinnhan.idnhom", "banSao"));
            var ars = resultToList(rs);
            ars.sort((TinNhan t1, TinNhan t2) -> t1.getThoiGian().compareTo(t2.getThoiGian()));
            ars.forEach(item->{
                System.out.println(item);
            });
            return ars;
        } catch (Throwable ex) {
            return new ArrayList<>();
        }
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
        while (rs.next()) {
            if (rs.getMetaData().getColumnCount() == 1) {
                //
            } else {
                result.add(new TinNhan(
                        rs.getNString(1),
                        rs.getTimestamp(2),
                        rs.getNString(3),
                        rs.getNString(4),
                        rs.getNString(5),
                        rs.getNString(6),
                        rs.getNString(7)));
            }
        }
        return result;
    }

    @Override
    public ArrayList<TinNhan> insert(TinNhan t) {
        try {
            String insertQuery = t.toDelimitedList();

            var rs = database_helper.insert(database_query_builder.insert(tableName,
                    insertQuery
            ));

            TinNhan messageCopy = new TinNhan(t.getID(),
                    t.getThoiGian(),
                    t.getNoiDung(),
                    t.getNguoiGui(),
                    t.getNguoiNhan(),
                    t.getIDNhom(),
                    t.getNguoiNhan());

            insertQuery = t.toDelimitedList2();

            database_helper.insert(database_query_builder.insert(tableName2,
                    insertQuery
            ));
            
            insertQuery = messageCopy.toDelimitedList2();

            database_helper.insert(database_query_builder.insert(tableName2,
                    insertQuery
            ));

            return new ArrayList<>(rs);
        } catch (SQLServerException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<TinNhan> update(TinNhan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(TinNhan t) {
        return -1;
    }

    public int delete(String conditions) {
        var rs = database_helper.delete(database_query_builder.delete(tableName,
                conditions
        ));
        return rs;
    }

}
