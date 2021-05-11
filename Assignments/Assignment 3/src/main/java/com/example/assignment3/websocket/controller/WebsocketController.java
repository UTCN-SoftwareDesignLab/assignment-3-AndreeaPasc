package com.example.assignment3.websocket.controller;

import com.example.assignment3.websocket.model.Message;
import com.example.assignment3.websocket.model.ReceiveMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebsocketController {

    @MessageMapping("/message")
    @SendTo("/topic/receive")
    public ReceiveMessage greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ReceiveMessage("Patient, " + HtmlUtils.htmlEscape(message.getPatientName()) + "has an appointment that starts at"
                + HtmlUtils.htmlEscape(message.getStartHour().toString()) + " and ends at"
                + HtmlUtils.htmlEscape(message.getEndHour().toString()) + "!");
    }
}