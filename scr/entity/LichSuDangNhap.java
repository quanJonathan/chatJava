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
public class LichSuDangNhap {

    String username;
    Date ngayDangNhap;
    Date ngayDangXuat;

    public LichSuDangNhap(String username, Date ngayDangNhap, Date ngayDangXuat) {
        this.username = username;
        this.ngayDangNhap = ngayDangNhap;
        this.ngayDangXuat = ngayDangXuat;
    }

    public String getUsername() {
        return username;
    }

    public Date getNgayDangNhap() {
        return ngayDangNhap;
    }

    public Date getNgayDangXuat() {
        return ngayDangXuat;
    }

    public String toDelimitedList() {
        return String.format("N'%s', '%s', '%s'",
                username,
                ngayDangNhap,
                ngayDangXuat);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %200s",
                username,
                ngayDangNhap,
                ngayDangXuat);

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
