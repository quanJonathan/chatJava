/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author HMBAO
 */
public class LichSuDangNhap extends Serializable{

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

    @Override
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
}
