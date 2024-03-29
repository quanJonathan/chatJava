/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

import entity.NhomChat;
import entity.ThanhVienNhomChat;
import entity.TinNhan;
import java.util.ArrayList;

public interface EventGroupChat {
    public void requestGroupData(NhomChat group);
    public void getGroupMember(NhomChat group);
    public void setGroup(NhomChat group);
    public void setGroupChatData(ArrayList<TinNhan> messages);
    public void sendMessage(NhomChat group, TinNhan message);
    public void receiveMessage(NhomChat group, TinNhan messages);
    public void deleteCurrentGroupData(NhomChat user);
    public void setAdmin(NhomChat group, ArrayList<ThanhVienNhomChat> users);
    public void setNewGroupName(NhomChat group, String newName);
    public void requestMemberData(NhomChat group);
    public void insertMember(String id, ArrayList<ThanhVienNhomChat> users);    
    public void removeMember(String id, ArrayList<ThanhVienNhomChat> users);
}
