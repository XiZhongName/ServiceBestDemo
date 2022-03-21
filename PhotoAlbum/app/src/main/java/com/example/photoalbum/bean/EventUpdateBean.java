package com.example.photoalbum.bean;

import java.util.ArrayList;
import java.util.List;

public class EventUpdateBean {

    public String message;

    public List<PicBean> getPicList() {
        return picList;
    }

    private List<PicBean> picList;
    public EventUpdateBean(String message,List<PicBean> picList) {
        this.message = message;
        this.picList=picList;
    }

    public String getMessage() {
        return message;
    }
}
