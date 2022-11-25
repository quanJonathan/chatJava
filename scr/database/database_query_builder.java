/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author HMBAO
 */
public class database_query_builder {

    final static String SELECT = "Select";
    final static String INSERT = "Insert";
    final static String UPDATE = "Update";
    final static String DELETE = "Delete";

    public static String getAll(String... tableNames) {
        String query;
        query = String.join(" ", SELECT, "*", "from", String.join(",", tableNames));
        return query;
    }

    public static String insert(String tableName, String insertValues) {
        String query;
        query = String.join(" ", INSERT, "into", tableName, "values(", insertValues + ")");
        return query;
    }

    public static String get(String tableName, String conditions, String... tableColumn) {
        String query;
        query = String.join(" ", SELECT, (tableColumn.length > 0 && !tableColumn[0].isEmpty()) ? String.join(",", tableColumn) : "*", "from", tableName, conditions);
        return query;
    }

    public static String delete(String tableName, String conditions) {
        String query;
        query = String.join(" ", DELETE, "from", tableName, conditions);
        return query;
    }

    public static void update() {
        // To Do
    }
}
