/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class TinNhan extends Serializable{

    String ID;
    Date thoiGian = new Date(System.currentTimeMillis());
    String noiDung;

    public TinNhan(String ID, Date thoiGian, String noiDung) {
        this.ID = ID;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
    }

    public String getID() {
        return ID;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    @Override
    public String toDelimitedList() {
        return String.format("'%s', '%s', N'%s'",
                ID,
                thoiGian,
                noiDung);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %200s",
                ID,
                thoiGian,
                noiDung);

    }
}
