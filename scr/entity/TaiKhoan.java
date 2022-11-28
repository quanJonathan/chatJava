/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HMBAO
 */
public class TaiKhoan {

    String username;
    String password;
    String email;
    Date ngaySinh = new Date(System.currentTimeMillis());
    boolean gioiTinh; // 0 is male
    String diaChi;
    boolean trangThai; // 1 is active

    public TaiKhoan(String name, String pass, String email) {
        username = name;
        password = pass;
        this.email = email;
    }

    public TaiKhoan setNgaySinh(Date d) {
        ngaySinh = d;
        return this;
    }

    // 0 is male
    public TaiKhoan setGioiTinh(boolean g) {
        gioiTinh = g;
        return this;
    }

    public TaiKhoan setDiaChi(String ad) {
        diaChi = ad;
        return this;
    }

    public TaiKhoan setTrangThai(boolean s) {
        trangThai = s;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public String toDelimitedList() {
        return String.format("N'%s', '%s', '%s', '%s', '%b', N'%s', '%b'",
                username,
                password,
                email,
                ngaySinh,
                gioiTinh,
                diaChi,
                trangThai);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-30s %-15s %-1s %-15s %-1s",
                username, password, email, ngaySinh != null ? ngaySinh.toString() : "",
                gioiTinh ? "Female" : "Male", diaChi, trangThai ? "Online" : "Offline");

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

    public ArrayList<String> toPair() {
        try {
            var list = new ArrayList<String>();
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    list.add(String.format("%s = N'%s'", field.getName(), field.get(this)));
                } catch (IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            return list;
        } catch (JSONException ex) {
            return new ArrayList<>();
        }
    }
}
