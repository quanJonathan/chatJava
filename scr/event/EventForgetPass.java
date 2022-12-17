package event;

public interface EventForgetPass {

    public void sendPasswordResetMail(String email, String username);

    public void goback();
    
    public void findAccount(String username);
    
    public void accountResult(String email, boolean b);

    public void sendCode(String username, String code);
    
    public void setID(String id);

    public void sendEnterCode(String username, String enterCode);

    public void codeChecked(String id, boolean checked);
    
    public void sendPassword(String username, String password);
    
    public void checkResult(boolean b);
}
