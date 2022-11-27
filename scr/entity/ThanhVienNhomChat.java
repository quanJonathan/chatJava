/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.lang.reflect.Field;
import java.sql.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HMBAO
 */
public class ThanhVienNhomChat {

    String IDNhom;
    String username;
    boolean chucNang; // 0 = user, 1 = admin
    Date ngayThem;

    public ThanhVienNhomChat(String IDNhom, String username, boolean chucNang, Date ngayThem) {
        this.IDNhom = IDNhom;
        this.username = username;
        this.chucNang = chucNang;
        this.ngayThem = ngayThem;
    }

    public String getIDNhom() {
        return IDNhom;
    }

    public String getUsername() {
        return username;
    }

    public boolean isChucNang() {
        return chucNang;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public String toDelimitedList() {
        return String.format("N'%s', N'%s', '%b', '%s",
                IDNhom,
                username,
                chucNang,
                ngayThem);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-10s %-10s",
                IDNhom,
                username,
                chucNang ? "Admin" : "Member",
                ngayThem);

    }

    public JSONObject JSONify() {
        try {
            JSONObject object = new JSONObject();
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    object.put(field.getName(), field.get(this));
                } catch (IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            return object;
        } catch (JSONException ex) {
            return null;
        }
    }
}
