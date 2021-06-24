package app.views.env.entities;

import java.io.Serializable;
import java.util.List;

public class Messages implements Serializable {
    public static Messages instance = null;

    public Messages() {

    }

    public synchronized static Messages getInstance() {
        if(instance != null) {
            instance = new Messages();
        }
        return instance;
    }

    public List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public synchronized void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
