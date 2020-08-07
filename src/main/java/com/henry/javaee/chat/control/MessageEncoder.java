package com.henry.javaee.chat.control;

import com.henry.javaee.chat.entity.Message;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("author", message.getAuthor())
                .add("content", message.getContent())
                .build().toString();
    }
}
