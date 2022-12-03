package event;

public class PublicEvent {

    private static PublicEvent instance;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventRegister eventRegister;
    private EventForgetPass eventForgetPass;
    private EventChatList eventChatList;
    private EventOnChatCard eventChatCard;
    private EventFriend eventFriend;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    public EventOnChatCard getEventChatCard() {
        return eventChatCard;
    }

    public void setEventChatCard(EventOnChatCard eventChatCard) {
        this.eventChatCard = eventChatCard;
    }

    public EventFriend getEventFriend() {
        return eventFriend;
    }

    public void addEventFriend(EventFriend eventFriend) {
        this.eventFriend = eventFriend;
    }

    private PublicEvent() {}

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
    
    public void addEventChatCard(EventOnChatCard event){
        this.eventChatCard = event;
    }
    
    public void addEventChatList(EventChatList event){
        this.eventChatList = event;
    }
     
    public EventChatList getEventChatList() {
        return eventChatList;
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
    
    public EventOnChatCard getEventOnChatCard(){
        return eventChatCard;
    } 
}
