package com.example.interfacecallbackdemo;

public class CallBackUtils {
    private static CallBack mCallBack;
    public static void setCallBack(CallBack callBack){
        mCallBack = callBack;
    }
    public static void doCallBackMethod(){
        String info = "这里CallBackUtils即将发送的数据";
        mCallBack.doSomeThing(info);
    }
}
