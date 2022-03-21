package com.example.interfacecallback;

public class Data {
    public Listener listener;

    public Data(Listener listener) {
        this.listener = listener;
    }
    public void sends(){
        listener.send("越努力的人，越聚贵人");
    }
}
