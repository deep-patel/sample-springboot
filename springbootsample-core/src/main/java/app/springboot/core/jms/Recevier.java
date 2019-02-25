package app.springboot.core.jms;

import app.springboot.core.dto.Notification;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by: deep.patel on 25/02/19
 */

@Component
public class Recevier {
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Notification notification) {
        System.out.println("Received <" + notification + ">");
    }
}
