package com.example.uibestpractice;

public class Msg {
    public static final int TYPE_RECEIVED = 0;//收到的消息
    public static final int TYPE_SENT = 0;//发送的消息
    private String content;//消息的内容
    private int type;//消息的类型

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
