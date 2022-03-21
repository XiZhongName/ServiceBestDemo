package com.example.order.utils;

import com.example.order.bean.ShopBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/*
* 单例
* */
public class JsonParse {
    //3.定义一个静态的对象
    private static JsonParse instance;
    //1.将构造方法私有化，让别人无法new
    private JsonParse(){}
    //2.提供一个方法获取实例,加synchronized保证单例
    public synchronized static JsonParse getInstance(){
        if (instance == null){
            instance = new JsonParse();
        }
        return instance;
    }
    public List<ShopBean> getShopList(String json){
        Type listType = new TypeToken<List<ShopBean>>(){}.getType();
        new Gson().fromJson(json,listType);

        return new Gson().fromJson(json,listType);
    }
}





































