/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author HMBAO
 */
public class TaiKhoan extends Serializable{

    String username;
    String fullName;
    String password;
    String email;
    Date ngaySinh = new Date(System.currentTimeMillis());
    boolean gioiTinh; // 0 is male
    String diaChi;
    int trangThai; // 1 is active

    public TaiKhoan(String name, String pass, String email) {
        username = name;
        password = pass;
        this.email = email;
    }
    
    public TaiKhoan setFullName(String fn) {
        fullName = fn;
        return this;
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

    public TaiKhoan setTrangThai(int s) {
        trangThai = s;
        return this;
    }
    
    public String getFullName() {
        return fullName;
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

    public int getTrangThai() {
        return trangThai;
    }

    @Override
    public String toDelimitedList() {
        return String.format("N'%s', '%s', N'%s', '%s', '%s', '%b', N'%s', '%d'",
                username,
                password,
                fullName,
                email,
                ngaySinh,
                gioiTinh,
                diaChi,
                trangThai);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-30s %-35s %-15s %-1s %-15s %-1s",
                username, password, email, fullName, ngaySinh != null ? ngaySinh.toString() : "",
                gioiTinh ? "Female" : "Male", diaChi, trangThai == 1 ? "Online" : "Offline");

    }
}
