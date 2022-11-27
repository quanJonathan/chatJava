/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HMBAO
 */
public class DanhSachTinNhan {

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

    public JSONObject JSONify() {
        try {
            JSONObject object = new JSONObject();
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    object.put(field.getName(), field.get(this));
                } catch (IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            return object;
        } catch (JSONException ex) {
            return null;
        }
    }
}
