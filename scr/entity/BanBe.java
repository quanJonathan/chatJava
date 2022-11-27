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
public class BanBe {

    String usernameChinh;
    String usernameBanBe;
    Date ngayKetBan;

    public BanBe(String me, String them, Date d) {
        usernameChinh = me;
        usernameBanBe = them;
        ngayKetBan = d;
    }

    public String getUsernameChinh() {
        return usernameChinh;
    }

    public String getUsernameBanBe() {
        return usernameBanBe;
    }

    public Date getNgayKetBan() {
        return ngayKetBan;
    }

    public String toDelimitedList() {
        return String.format("'%s', '%s', '%s'",
                usernameChinh,
                usernameBanBe,
                ngayKetBan.toString());
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s",
                usernameChinh,
                usernameBanBe,
                ngayKetBan.toString());

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
