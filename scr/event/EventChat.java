package event;

import entity.TinNhan;
import java.util.ArrayList;
import entity.TaiKhoan;

public interface EventChat {
    public void sendMessage(String text, String currentChatter);
    
    public void receiveMessage(TinNhan mess, String username);
    
    public void setAllChat(ArrayList<TaiKhoan> users);
    
    public void selectUser(String username);
    
    public void setChatData(ArrayList<TinNhan> messages);
}
