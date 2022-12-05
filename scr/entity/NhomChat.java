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
public class NhomChat extends Serializable{
 
    String IDNhom;
    String tenNhom;
    Date ngayTao = new Date(System.currentTimeMillis());

    public NhomChat(String IDNhom, String tenNhom, Date ngayTao) {
        this.IDNhom = IDNhom;
        this.tenNhom = tenNhom;
        this.ngayTao = ngayTao;
    }

    public String getIDNhom() {
        return IDNhom;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    @Override
    public String toDelimitedList() {
        return String.format("'%s', '%s', '%s'",
                IDNhom,
                tenNhom,
                ngayTao.toString());
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s",
                IDNhom,
                tenNhom,
                ngayTao.toString());

    }
}
