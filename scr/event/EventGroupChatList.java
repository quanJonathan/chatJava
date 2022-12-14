/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

import entity.NhomChat;
import entity.TaiKhoan;
import entity.ThanhVienNhomChat;
import java.util.ArrayList;

public interface EventGroupChatList {
    public void setData(ArrayList<NhomChat> groups, ArrayList<Boolean> roles);
    
    public void newGroup(NhomChat group, boolean role);
    public void setMember(ArrayList<ThanhVienNhomChat> members);
}
