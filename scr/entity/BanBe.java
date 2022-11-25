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
public class BanBe {

    String usernameChinh;
    String usernameBanBe;
    Date ngayKetBan;

    public BanBe(String me, String them, Date d) {
        usernameChinh = me;
        usernameBanBe = them;
        ngayKetBan = d;
    }

    public String getUsernameChinh() {
        return usernameChinh;
    }

    public String getUsernameBanBe() {
        return usernameBanBe;
    }

    public Date getNgayKetBan() {
        return ngayKetBan;
    }

    public String toDelimitedList() {
        return String.format("'%s', '%s', '%s'",
                usernameChinh,
                usernameBanBe,
                ngayKetBan.toString());
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s",
                usernameChinh,
                usernameBanBe,
                ngayKetBan.toString());

    }

}
