/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import entity.TaiKhoan;

/**
 *
 * @author ADMIN
 */
public interface EventRegister {
    void register(TaiKhoan user);
    void goback();
    void showDialog(String result, String title);
}
