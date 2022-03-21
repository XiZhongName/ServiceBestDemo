package com.example.photoalbum.bean;

public class PhotoBean {
    private String photoName;
    private int imgResId;
    private boolean checkBoxShow;
    private String imgPath;
    private boolean isCheck;

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public boolean isCheckBoxShow() {
        return checkBoxShow;
    }

    public void setCheckBoxShow(boolean checkBoxShow) {
        this.checkBoxShow = checkBoxShow;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
