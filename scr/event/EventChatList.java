/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

import entity.TaiKhoan;
import java.util.List;

public interface EventChatList {
    public void newUser(List<TaiKhoan> users);
    
    public void userConnect(TaiKhoan username);
    
    public void userDisconnect(TaiKhoan username);
    
}
