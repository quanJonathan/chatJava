/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HMBAO
 */
public interface DAO<T> {
    // Optional<T> get(long id);

    ArrayList<T> selectAll();

    ArrayList<T> select(String condition);

    ArrayList<T> resultToList(ResultSet rs) throws SQLException;

    ArrayList<T> insert(T t);

    ArrayList<T> update(T t);

    int delete(T t);
}
