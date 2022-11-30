/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class TinNhan extends Serializable {

    String ID;
    Date thoiGian = new Date(System.currentTimeMillis());
    String noiDung;
    String nguoiGui;
    String nguoiNhan;
    String IDNhom;

    public TinNhan(String ID, Date d, String noiDung, String nguoiGui, String nguoiNhan, String IDNhom) {
        this.ID = ID;
        this.thoiGian = d;
        this.noiDung = noiDung;
        this.nguoiGui = nguoiGui;
        this.nguoiNhan = nguoiNhan;
        this.IDNhom = IDNhom;
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

    public String getNguoiGui() {
        return nguoiGui;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public String getIDNhom() {
        return IDNhom;
    }

    @Override
    public String toDelimitedList() {
        return String.format("'%s', '%s', N'%s'",
                ID,
                thoiGian,
                noiDung);
    }

    public String toDelimitedList2() {
        return String.format("N'%s', N'%s', '%s'",
                nguoiGui,
                nguoiNhan,
                IDNhom);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-200s",
                ID,
                thoiGian,
                nguoiGui,
                nguoiNhan,
                IDNhom,
                noiDung);

    }
}
