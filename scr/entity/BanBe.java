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
public class BanBe extends Serializable{

    String username;
    String usernameBanBe;
    Date ngayKetBan = new Date(System.currentTimeMillis());

    ;

    public BanBe(String me, String them, Date d) {
        username = me;
        usernameBanBe = them;
        ngayKetBan = d;
    }

    public String getUsernameChinh() {
        return username;
    }

    public String getUsernameBanBe() {
        return usernameBanBe;
    }

    public Date getNgayKetBan() {
        return ngayKetBan;
    }

    @Override
    public String toDelimitedList() {
        return String.format("N'%s', N'%s', '%s'",
                username,
                usernameBanBe,
                ngayKetBan.toString());
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s",
                username,
                usernameBanBe,
                ngayKetBan.toString());

    }
}
