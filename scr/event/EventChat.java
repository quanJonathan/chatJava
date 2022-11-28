package event;

public interface EventChat {
    public void sendMessage(String text);
    
    public void setChatter(String name);
    
    public String getMessage();
}
