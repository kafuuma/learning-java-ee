package com.henry.javaee.chat.control;

import com.henry.javaee.chat.entity.Message;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String string) throws DecodeException {
        JsonObject  jsonObject =  Json.createReader(new StringReader(string)).readObject();

        String author = jsonObject.getString("author");
        String content = jsonObject.getString("content");
        return new Message(author, content);
    }

    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
