package com.example.weatherapp321.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *       "day":"06日（星期四）",
 *             "date":"2022-01-06",
 *             "week":"星期四",
 *             "wea":"雾转阴",
 *             "wea_img":"yin",
 *             "wea_day":"雾",
 *             "wea_day_img":"wu",
 *             "wea_night":"阴",
 *             "wea_night_img":"yin",
 *             "tem":"1℃",
 *             "tem1":"1℃",
 *             "tem2":"0℃",
 *             "humidity":"90%",
 *             "visibility":"1km",
 *             "pressure":"953",
 *             "win":Array[2],
 *             "win_speed":"<3级",
 *             "win_meter":"0km/h",
 *             "sunrise":"07:49",
 *             "sunset":"17:46",
 *             "air":"104",
 *             "air_level":"轻度污染",
 *             "air_tips":"儿童、老年人及心脏病、呼吸系统疾病患者应尽量减少体力消耗大的户外活动。",
 *             "alarm":Object{...},
 *             "hours":Array[10],
 *             "index":Array[6]
 *
 * */
public class DayWeatherBean implements Serializable {
    @SerializedName("day")
    private String day;
    @SerializedName("date")
    private String date;
    @SerializedName("week")
    private String week;
    @SerializedName("wea")
    private String wea;
    @SerializedName("wea_img")
    private String weaImg;
    @SerializedName("wea_day")
    private String weaDay;
    @SerializedName("tem")
    private String tem;
    @SerializedName("tem1")
    private String tem1;
    @SerializedName("tem2")
    private String tem2;
    @SerializedName("win")
    private String[] win;
    @SerializedName("win_speed")
    private String winSpeed;
    @SerializedName("air")
    private String air;
    @SerializedName("air_level")
    private String airLevel;
    @SerializedName("air_tips")
    private String airTips;
    @SerializedName("index")
    private List<OtherTipsBean> mTipsBeans;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    public String getWeaDay() {
        return weaDay;
    }

    public void setWeaDay(String weaDay) {
        this.weaDay = weaDay;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String[] getWin() {
        return win;
    }

    public void setWin(String[] win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getAirLevel() {
        return airLevel;
    }

    public void setAirLevel(String airLevel) {
        this.airLevel = airLevel;
    }

    public String getAirTips() {
        return airTips;
    }

    public void setAirTips(String airTips) {
        this.airTips = airTips;
    }

    public List<OtherTipsBean> getmTipsBeans() {
        return mTipsBeans;
    }

    public void setmTipsBeans(List<OtherTipsBean> mTipsBeans) {
        this.mTipsBeans = mTipsBeans;
    }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "day='" + day + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", wea='" + wea + '\'' +
                ", weaImg='" + weaImg + '\'' +
                ", weaDay='" + weaDay + '\'' +
                ", tem='" + tem + '\'' +
                ", tem1='" + tem1 + '\'' +
                ", tem2='" + tem2 + '\'' +
                ", win=" + Arrays.toString(win) +
                ", winSpeed='" + winSpeed + '\'' +
                ", air='" + air + '\'' +
                ", airLevel='" + airLevel + '\'' +
                ", airTips='" + airTips + '\'' +
                ", mTipsBeans=" + mTipsBeans +
                '}';
    }
}




















































