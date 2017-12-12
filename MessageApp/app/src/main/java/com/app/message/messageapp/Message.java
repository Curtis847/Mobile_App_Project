package com.app.message.messageapp;

/**
 * Created by curti on 12/12/2017.
 */

public class Message {
    private String content;

    public Message(){

    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
