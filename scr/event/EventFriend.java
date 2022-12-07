/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import entity.BanBe;
import entity.TaiKhoan;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public interface EventFriend {
    public void setData(ArrayList<BanBe> friendList);
    
    public void unfriend(BanBe user);
}
