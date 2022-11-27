/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.lang.reflect.Field;
import java.sql.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HMBAO
 */
public class NhomChat {

    String IDNhom;
    String tenNhom;
    Date ngayTao;

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
