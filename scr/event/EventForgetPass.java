
package event;

public interface EventForgetPass {
    public void sendPasswordResetMail(String email, String username);
    public void goback();
}
