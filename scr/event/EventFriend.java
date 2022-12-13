/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import entity.BanBe;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface EventFriend {

    public void setData(ArrayList<BanBe> friendList);

    public void setFriendSearchData(ArrayList<BanBe> friendList);

    public void getFriendSearchData(String text);

    public void setFriendRequestData(ArrayList<BanBe> friendList);

    public void getFriendRequestData(String user);

    public void addFriend(String usernameBanBe);

    public void unfriend(BanBe user);
}
