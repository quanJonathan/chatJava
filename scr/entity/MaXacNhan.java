/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MaXacNhan extends Serializable {

    String id;
    String username;
    String code;
    Date thoiGian;

    public MaXacNhan(String id, String username, String code, Date thoiGian) {
        this.id = id;
        this.username = username;
        this.code = code;
        this.thoiGian = thoiGian;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCode() {
        return code;
    }

    public Date getThoiGian() {
        return thoiGian;
    }
    
    @Override
    public String toDelimitedList() {
        return String.format("N'%s', N'%s', '%s', '%s'",
                id,
                username,
                code,
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(thoiGian));
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-15s %-20s",
                id,
                username,
                code,
                thoiGian.toString());

    }

}
