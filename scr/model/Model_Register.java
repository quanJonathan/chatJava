/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class Model_Register {
    String username;
    String password;
    String sex;
    String address;
    String email;

    public Model_Register(String username, String password, String sex, String address, String email) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.address = address;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public JSONObject toJSONObject(){
        try {
            JSONObject object = new JSONObject();
            object.put("username", username);
            object.put("email", email);
            object.put("sex", sex);
            object.put("address", address);
            object.put("password", password);
            return object; 
        } catch (JSONException ex) {
            return null;
        }
        
    }
}
