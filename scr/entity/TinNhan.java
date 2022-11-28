/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.lang.reflect.Field;
import java.sql.Date;
import org.json.JSONException;
import org.json.JSONObject;
import database.StringRandomizer;
import static entity.IDPrefix.IDTinNhan;

/**
 *
 * @author ADMIN
 */
public class TinNhan {

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
