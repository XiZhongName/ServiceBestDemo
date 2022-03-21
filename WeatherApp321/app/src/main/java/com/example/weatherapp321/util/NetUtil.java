package com.example.weatherapp321.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    public static final String URL_WEATHER_WITH_FUTURE = "https://yiketianqi.com/api?unescape=1&version=v1&appid=82793424&appsecret=45D7K6e4";
    public static String doGet(String urlStr){
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        //连接网络
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);//设置超时时间

            //从连接中读取数据（二进制）
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            //二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);//缓冲区
            //从缓冲区中一行行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            // 每次读一行 循环读取
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.disconnect();//关闭connection
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    public static String getWeatherOfCity(String city){
        //拼接出获取到的天气数据的URL
        //https://yiketianqi.com/api?unescape=1&version=v1&appid=82793424&appsecret=45D7K6e4
        String weatherUrl = URL_WEATHER_WITH_FUTURE + "&city=" + city;
        String weatherResult = doGet(weatherUrl);
        return weatherResult;
    }
}
