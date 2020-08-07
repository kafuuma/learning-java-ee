package com.henry.javaee.chat.boundary;

import com.henry.javaee.chat.control.MessageDecoder;
import com.henry.javaee.chat.control.MessageEncoder;
import com.henry.javaee.chat.entity.Message;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/chart", encoders = MessageEncoder.class,  decoders = MessageDecoder.class)
public class MessageServer {
    @Inject
    ChatResponder chatResponder;

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        System.out.println("new message from author "+ message.getAuthor());
       Message response = chatResponder.respond(message);
       session.getBasicRemote().sendObject(response);
    }

}
