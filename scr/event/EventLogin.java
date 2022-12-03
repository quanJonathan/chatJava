package event;

import entity.TaiKhoan;


public interface EventLogin {

    public void login();

    public void goRegister();
    
    public void forgetPass();
    
    public void goLogin(TaiKhoan user);
}
