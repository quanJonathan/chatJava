package event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventMain eventMain;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventRegister eventRegister;
    private EventForgetPass eventForgetPass;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {}

    public void addEventMain(EventMain event) {
        this.eventMain = event;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }

    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }

    public void addEventRegister(EventRegister event) {
        this.eventRegister = event;
    }
    
    public void addEventForgetPass(EventForgetPass event){
        this.eventForgetPass = event;
    }

    public EventMain getEventMain() {
        return eventMain;
    }
    
    public EventRegister getEventRegister() {
        return eventRegister;
    }
    
    public EventChat getEventChat() {
        return eventChat;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }
    
    public EventForgetPass getEventForgetPass() {
        return eventForgetPass;
    }
    
}
