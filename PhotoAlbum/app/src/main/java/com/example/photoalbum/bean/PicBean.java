package com.example.photoalbum.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PicBean implements Parcelable {
    public PicBean(Parcel in) {
        checkBoxShow = in.readByte() != 0;
        isCheck = in.readByte() != 0;
        imgPath = in.readString();
    }

    public static final Creator<PicBean> CREATOR = new Creator<PicBean>() {
        @Override
        public PicBean createFromParcel(Parcel in) {
            return new PicBean(in);
        }

        @Override
        public PicBean[] newArray(int size) {
            return new PicBean[size];
        }
    };

    public PicBean() {

    }

    public boolean isCheckBoxShow() {
        return checkBoxShow;
    }

    public void setCheckBoxShow(boolean checkBoxShow) {
        this.checkBoxShow = checkBoxShow;
    }

    private boolean checkBoxShow;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck;
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    private String imgPath;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (checkBoxShow ? 1 : 0));
        dest.writeByte((byte) (isCheck ? 1 : 0));
        dest.writeString(imgPath);
    }
}
