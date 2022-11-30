/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HMBAO
 */
public abstract class Serializable {
    public abstract String toDelimitedList();    
    @Override
    public abstract String toString();
    
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

    public ArrayList<String> toPair() {
        try {
            var list = new ArrayList<String>();
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    list.add(String.format("%s = N'%s'", field.getName(), field.get(this)));
                } catch (IllegalAccessException ex) {
                    System.out.println(ex);
                }
            }
            return list;
        } catch (JSONException ex) {
            return new ArrayList<>();
        }
    }
}
