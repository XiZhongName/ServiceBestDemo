package com.example.photoalbum.bean;

/**
 * 用户
 */
public class User {
    private String id;
    private String account;//账号
    private String name;//名称
    private String password;//密码
    private String photo;//头像
    private String sex;//性别

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(String id, String account, String name, String password, String photo, String sex) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.sex = sex;
    }
}
