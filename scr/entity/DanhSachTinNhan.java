/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HMBAO
 */
public class DanhSachTinNhan extends Serializable{

    String ID;
    String nguoiGui;
    String nguoiNhan;
    String IDNhom;

    public DanhSachTinNhan(String ID, String nguoiGui, String nguoiNhan, String IDNhom) {
        this.ID = ID;
        this.nguoiGui = nguoiGui;
        this.nguoiNhan = nguoiNhan;
        this.IDNhom = IDNhom;
    }

    public String getID() {
        return ID;
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
        return String.format("N'%s', N'%s', N'%s', '%s",
                IDNhom,
                nguoiGui,
                nguoiNhan,
                IDNhom);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-10s %-10s",
                IDNhom,
                nguoiGui,
                nguoiNhan,
                IDNhom);

    }
}
