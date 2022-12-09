/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HMBAO
 */
public interface DAO<T> {
    // Optional<T> get(long id);

    List<T> selectAll();

    List<T> select(String condition);

    List<T> resultToList(ResultSet rs) throws SQLException;

    List<T> insert(T t);

    List<T> update(T t);

    int delete(T t);
}
