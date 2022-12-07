/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;
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
    String banSao;

    public TinNhan(String ID, Date d, String noiDung, String nguoiGui, String nguoiNhan, String IDNhom, String banSao) {
        this.ID = ID;
        this.thoiGian = d;
        this.noiDung = noiDung;
        this.nguoiGui = nguoiGui;
        this.nguoiNhan = nguoiNhan;
        this.IDNhom = IDNhom;
        this.banSao = banSao;
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

    public String getBanSao() {
        return banSao;
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
        if (IDNhom.isEmpty()) {
            return String.format("'%s', N'%s', N'%s', %s, N'%s'",
                ID,
                nguoiGui,
                nguoiNhan,
                null,
                banSao);
        }
        return String.format("'%s', N'%s', N'%s', '%s', N'%s'",
                ID,
                nguoiGui,
                nguoiNhan,
                IDNhom,
                banSao);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-25s %-15s %-15s %-15s %-15s %-200s",
                ID,
                thoiGian,
                nguoiGui,
                nguoiNhan,
                IDNhom,
                banSao,
                noiDung);

    }
}
